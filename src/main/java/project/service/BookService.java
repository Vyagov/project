package project.service;

import project.model.service.BookServiceModel;
import project.model.view.BookViewModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface BookService {
    void addBook(BookServiceModel bookServiceModel, Principal principal) throws IOException;

    List<BookServiceModel> findAllBooks();

    List<BookViewModel> getAllBooks();

    BookViewModel findById(String id);

    void delete(String id);
}
