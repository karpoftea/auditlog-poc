package org.acme.auditlogpoc;

public class BookDto {

    private Long id;
    private String isbn;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookDto{" +
               "id=" + id +
               ", isbn='" + isbn + '\'' +
               ", title='" + title + '\'' +
               '}';
    }
}
