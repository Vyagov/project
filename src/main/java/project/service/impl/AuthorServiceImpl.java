package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.entity.Author;
import project.model.service.AuthorServiceModel;
import project.model.view.AuthorViewModel;
import project.repository.AuthorRepository;
import project.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAuthor(AuthorServiceModel authorServiceModel) {
        this.authorRepository.saveAndFlush(this.modelMapper.map(authorServiceModel, Author.class));
    }

    @Override
    public List<AuthorViewModel> findAllAuthors() {
        return this.authorRepository
                .findAll()
                .stream()
                .map(this::authorToView)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorViewModel findById(String id) {
        return this.authorRepository
                .findById(id)
                .map(this::authorToView)
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        this.authorRepository.deleteById(id);
    }

    @Override
    public Author findByName(String name) {
        return this.authorRepository
                .findByName(name)
                .orElse(null);
    }

    private AuthorViewModel authorToView(Author author) {
        return this.modelMapper.map(author, AuthorViewModel.class);
    }
}
