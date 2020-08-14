package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.model.entity.Author;
import project.model.entity.Book;
import project.model.service.BookServiceModel;
import project.model.view.BookViewModel;
import project.repository.BookRepository;
import project.service.AuthorService;
import project.service.BookService;
import project.service.CloudinaryService;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           ModelMapper modelMapper,
                           CloudinaryService cloudinaryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addBook(BookServiceModel bookServiceModel) throws IOException {
        Book book = this.modelMapper.map(bookServiceModel, Book.class);
        Author author = this.authorService.findByName(bookServiceModel.getAuthor().getName());
        book.setAuthor(author);

        MultipartFile image = bookServiceModel.getImage();

        if (!image.isEmpty()) {
            String imgUrl = this.cloudinaryService.uploadImage(image);
            book.setPathToImage(imgUrl);
        } else {
            book.setPathToImage("/img/empty-book.jpg");
        }
        this.bookRepository.saveAndFlush(book);
    }

    @Override
    public List<BookViewModel> findAllProducts() {
        return null;
    }

    @Override
    public BookViewModel findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
