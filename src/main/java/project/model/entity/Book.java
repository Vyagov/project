package project.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    private String bookTitle;
    private String genre;
    private Integer pageCount;
    private String resume;
    private LocalDate issueDate;
    private String isbn;    /*International Standard Book Number (ISBN)*/
    private byte[] image;
    private Author author;

    public Book() {
    }

    @Column(name = "book_title", unique = true, nullable = false)
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Column(name = "genre", nullable = false)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(name = "page_count", nullable = false)
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Column(name = "resume", columnDefinition = "TEXT", nullable = false)
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Column(name = "ussue_data", nullable = false)
    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Column(name = "isbn", nullable = false)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Lob
    @Column(name="image", nullable=false, columnDefinition="mediumblob")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @ManyToOne(cascade = ALL, fetch = EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
