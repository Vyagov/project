package project.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.annotation.Title;
import project.error.*;
import project.init.Tools;
import project.model.binding.UserRegisterBindingModel;
import project.model.service.UserServiceModel;
import project.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Tools tools;

    @Autowired
    public UsersController(UserService userService, ModelMapper modelMapper,
                           Tools tools) {
        this.tools = tools;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Title(name = "User Login")
    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }

    @PostMapping("/login-error")
    public ModelAndView loginError(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.
                    SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            ModelAndView modelAndView
    ) {
        modelAndView.addObject("error", "bad.credentials");
        modelAndView.addObject("username", username);
        modelAndView.setViewName("auth/login");

        return modelAndView;
    }

    @Title(name = "User register")
    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                          UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            return "redirect:/users/register";
        }
        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:/users/login";
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
