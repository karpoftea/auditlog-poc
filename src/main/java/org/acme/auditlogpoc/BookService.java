package org.acme.auditlogpoc;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookDto create(BookDto bookDto) {
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        Book saved = bookRepository.save(book);

        bookDto.setId(saved.getId());
        bookDto.setTitle(saved.getTitle());
        bookDto.setIsbn(saved.getIsbn());
        return bookDto;
    }

    @Transactional(readOnly = true)
    public Optional<BookDto> getById(Long id) {
        return bookRepository.findById(id).map(book -> {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setTitle(book.getTitle());
            return bookDto;
        });
    }

    @Transactional
    public void update(Long id, BookDto bookDto) {
        Book updatedBook = bookRepository.findById(id).map(book -> {
            book.setTitle(bookDto.getTitle());
            book.setIsbn(bookDto.getIsbn());
            return book;
        }).orElseThrow(() -> new RuntimeException("book not found:" + bookDto.getId()));
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
