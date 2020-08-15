package project.model.binding;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import project.model.enums.GenreName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

public class BookBindingModel {
    private String bookTitle;
    private GenreName genre;
    private Integer pageCount;
    private String summary;
    private String issueDate;
    private String isbn;    /*International Standard Book Number (ISBN)*/
    private MultipartFile image;
    private String author;

    public BookBindingModel() {
    }

    @NotBlank(message = "Not be empty!")
    @Length(min = 2, max = 200, message = "The title length must be between 2 and 200 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9-\\s+,.!'\"_\\p{IsCyrillic}]+$", message = "Is not a text!")
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @NotNull(message = "Select a genre!")
    @Enumerated(EnumType.STRING)
    public GenreName getGenre() {
        return genre;
    }

    public void setGenre(GenreName genre) {
        this.genre = genre;
    }

    @NotNull(message = "Not be empty!")
    @Digits(integer = 5, fraction = 0, message = "Length of value is not in range! ")
    @Min(value = 1, message = "The page count is not correct! ")
    @Max(value = 20000, message = "The number of pages is too large!")
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @NotBlank(message = "Not be empty!")
    @Length(min = 10, message = "Summary length must be more than 10 characters! ")
    @Pattern(regexp = "^[a-zA-Z0-9-\\s+,.!\"_\\p{IsCyrillic}]+$", message = "Is not a text!")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @NotNull(message = "Date can not be in the future!")
    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @ISBN(message = "Enter valid a 13-digit ISBN code!")
    @NotNull(message = "Not be empty!")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @NotNull(message = "Select an image!")
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @NotBlank(message = "Select an author!")
    @NotNull(message = "Select an author!")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
