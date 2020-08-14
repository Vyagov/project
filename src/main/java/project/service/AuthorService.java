package project.service;

import project.model.entity.Author;
import project.model.service.AuthorServiceModel;
import project.model.view.AuthorViewModel;

import java.util.List;

public interface AuthorService {

    void addAuthor(AuthorServiceModel authorServiceModel);

    List<AuthorViewModel> findAllAuthors();

    AuthorViewModel findById(String id);

    void delete(String id);

    Author findByName(String name);
}
