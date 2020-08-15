package project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.model.entity.Author;
import project.model.entity.Book;
import project.model.entity.User;
import project.model.service.BookServiceModel;
import project.model.service.UserServiceModel;
import project.model.view.BookViewModel;
import project.repository.BookRepository;
import project.service.AuthorService;
import project.service.BookService;
import project.service.CloudinaryService;
import project.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           ModelMapper modelMapper,
                           CloudinaryService cloudinaryService,
                           UserService userService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @Override
    public void addBook(BookServiceModel bookServiceModel, Principal principal) throws IOException {
        Book book = this.modelMapper.map(bookServiceModel, Book.class);

        Author author = this.authorService.findByName(bookServiceModel.getAuthor());
        book.setAuthor(author);

        UserServiceModel userServiceModel = this.userService.findUserByUsername(principal.getName());
        book.setUser(this.modelMapper.map(userServiceModel, User.class));

        LocalDate date = LocalDate.parse(bookServiceModel.getIssueDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        book.setIssueDate(date);

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
    public List<BookServiceModel> findAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(this::mapToService)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookViewModel> getAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(book -> this.modelMapper.map(book, BookViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookViewModel findById(String id) {
        return this.bookRepository
                .findById(id)
                .map(this::mapToView)
                .orElse(null);
    }

    @Override
    public void delete(String id) {

    }

    private BookServiceModel mapToService(Book book) {
        return this.modelMapper.map(book, BookServiceModel.class);
    }

    private BookViewModel mapToView(Book book) {
        return this.modelMapper.map(book, BookViewModel.class);
    }
}
