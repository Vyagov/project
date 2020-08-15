package project.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.annotation.Title;
import project.error.*;
import project.model.binding.AuthorBindingModel;
import project.model.service.AuthorServiceModel;
import project.service.AuthorService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @Title(name = "Add Author")
    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN', 'USER')")
    public String add(Model model) {
        if (!model.containsAttribute("authorBindingModel")) {
            model.addAttribute("authorBindingModel", new AuthorBindingModel());
        }
        return "author/add-author";
    }

    @Title(name = "Add Author")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN', 'USER')")
    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("authorBindingModel")
                                     AuthorBindingModel authorBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("authorBindingModel", authorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorBindingModel", bindingResult);

            return "redirect:add";
        }
        this.authorService.addAuthor(this.modelMapper.map(authorBindingModel, AuthorServiceModel.class));

        return "redirect:/books/add";
    }

    @Title(name = "Author Details")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN', 'USER')")
    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("author", this.authorService.findById(id));
        modelAndView.setViewName("author/details-author");

        return modelAndView;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        this.authorService.delete(id);

        return "redirect:/home";
    }

    @ExceptionHandler({UsernameAlreadyExistException.class,
            UsernameNotFoundException.class,
            UserRegistrationException.class,
            UsernameAlreadyExistException.class,
            UserIdNotFoundException.class,
            PasswordsNotMatchException.class})
    public ModelAndView handleUserException(CustomBaseException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msgError", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
