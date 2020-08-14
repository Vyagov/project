package project.model.view;

import org.springframework.web.multipart.MultipartFile;
import project.model.service.AuthorServiceModel;

import java.time.LocalDate;

public class BookViewModel extends BaseViewModel {
    private String bookTitle;
    private String genre;
    private Integer pageCount;
    private String summary;
    private LocalDate issueDate;
    private String isbn;    /*International Standard Book Number (ISBN)*/
    private MultipartFile image;
    private AuthorServiceModel author;

    public BookViewModel() {
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public AuthorServiceModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorServiceModel author) {
        this.author = author;
    }
}
