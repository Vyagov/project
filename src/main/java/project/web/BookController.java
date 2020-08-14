package project.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.annotation.Title;
import project.model.binding.BookBindingModel;
import project.service.AuthorService;
import project.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @Title(name = "Add Product")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN','ADMIN', 'USER')")
    @GetMapping("/add")
    public String productAdd(Model model) {

        if (!model.containsAttribute("bookBindingModel")) {
            model.addAttribute("bookBindingModel", new BookBindingModel());
            model.addAttribute("authors", this.authorService.findAllAuthors());
        }
        return "book/add-book";
    }


}
