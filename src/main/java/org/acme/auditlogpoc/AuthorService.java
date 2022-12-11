package org.acme.auditlogpoc;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.acme.auditlogpoc.event.EntityUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        Author saved = authorRepository.save(author);

        authorDto.setId(saved.getId());
        authorDto.setName(saved.getName());
        return authorDto;
    }

    @Transactional(readOnly = true)
    public Optional<AuthorDto> getById(Long id) {
        return authorRepository.findById(id).map(author -> {
            AuthorDto authorDto = toAuthorDto(author);
            return authorDto;
        });
    }

    private static AuthorDto toAuthorDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        return authorDto;
    }

    @Transactional
    public void update(Long id, AuthorDto authorDto) {
        log.info("Updating id:{}, dto:{}", id, authorDto);
        Author updatedAuthor = authorRepository.findById(id).map(author -> {
            author.setName(authorDto.getName());
            return author;
        }).orElseThrow(() -> new RuntimeException("author not found:" + authorDto.getId()));

        updatedAuthor = authorRepository.save(updatedAuthor);

        AuthorDto updatedAuthorDto = toAuthorDto(updatedAuthor);
        publisher.publishEvent(new EntityUpdatedEvent<>(authorDto, updatedAuthorDto));
    }

    @Transactional
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
