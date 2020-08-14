package project.service;

import project.model.service.BookServiceModel;
import project.model.view.BookViewModel;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void addBook(BookServiceModel bookServiceModel) throws IOException;

    List<BookViewModel> findAllProducts();

    BookViewModel findById(String id);

    void delete(String id);
}
