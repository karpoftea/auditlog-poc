package org.acme.auditlogpoc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookResource {

    private final BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Optional<BookDto> getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping("/")
    public BookDto create(@RequestBody BookDto bookDto) {
        return bookService.create(bookDto);
    }

    @PutMapping("/{id}")
    public void update(
        @PathVariable Long id,
        @RequestBody BookDto bookDto
    ) {
        bookService.update(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
