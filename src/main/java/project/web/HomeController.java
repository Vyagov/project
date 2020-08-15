package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.model.view.BookViewModel;
import project.service.BookService;

import java.util.List;

@Controller
public class HomeController {
    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<BookViewModel> allBooks = this.bookService.getAllBooks();
        model.addAttribute("allBooks", allBooks);
        return "home";
    }
}
