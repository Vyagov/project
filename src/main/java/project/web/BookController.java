package project.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.annotation.Title;
import project.init.CustomFileValidator;
import project.model.binding.BookBindingModel;
import project.model.service.BookServiceModel;
import project.service.AuthorService;
import project.service.BookService;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;
    private final CustomFileValidator customFileValidator;

    @Autowired
    public BookController(BookService bookService,
                          AuthorService authorService,
                          ModelMapper modelMapper,
                          CustomFileValidator customFileValidator) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
        this.customFileValidator = customFileValidator;
    }

    @Title(name = "Add Book")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN','ADMIN', 'USER')")
    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("authors")) {
            model.addAttribute("authors", this.authorService.findAllAuthors());
        }
        if (!model.containsAttribute("bookBindingModel")) {
            model.addAttribute("bookBindingModel", new BookBindingModel());
            model.addAttribute("errorData", false);
        }
        return "book/add-book";
    }

    @Title(name = "Add Book")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN', 'USER')")
    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("bookBindingModel") BookBindingModel bookBindingModel,
                             Principal principal,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {
        String date = bookBindingModel.getIssueDate();
        customFileValidator.validate(bookBindingModel, bindingResult);

        if (bindingResult.hasErrors()) {
            if (date.equals("")) {
                redirectAttributes.addFlashAttribute("errorData", true);
            } else {
                LocalDate localDate = LocalDate.parse(date);
                LocalDate timeNow = LocalDate.now();

                if (localDate.isAfter(timeNow)) {
                    redirectAttributes.addFlashAttribute("errorData", true);
                }
            }
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("bookBindingModel", bookBindingModel);
            return "redirect:/books/add";
        } else {
            if (date.equals("")) {
                redirectAttributes.addFlashAttribute("errorData", true);
                return "redirect:/books/add";
            }
            LocalDate localDate = LocalDate.parse(date);
            LocalDate timeNow = LocalDate.now();

            if (localDate.isAfter(timeNow)) {
                redirectAttributes.addFlashAttribute("errorData", true);
                return "redirect:/books/add";
            }
        }
        BookServiceModel bookServiceModel = this.modelMapper.map(bookBindingModel, BookServiceModel.class);
        this.bookService.addBook(bookServiceModel, principal);
        return "redirect:/home";
    }
}