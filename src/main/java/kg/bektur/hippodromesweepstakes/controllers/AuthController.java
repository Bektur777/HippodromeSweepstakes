package kg.bektur.hippodromesweepstakes.controllers;

import jakarta.validation.Valid;
import kg.bektur.hippodromesweepstakes.entities.User;
import kg.bektur.hippodromesweepstakes.services.AuthorizationService;
import kg.bektur.hippodromesweepstakes.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthorizationService authorizationService;

    private final UserValidator userValidator;

    @Autowired
    public AuthController(AuthorizationService authorizationService, UserValidator userValidator) {
        this.authorizationService = authorizationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registrationPage(@ModelAttribute("user") User User) {
        return "auth/registration";
    }

    @PostMapping("/register")
    public String performRegistration(@ModelAttribute("user") @Valid User User, BindingResult bindingResult) {
        userValidator.validate(User, bindingResult);

        if (bindingResult.hasErrors())
            return "auth/registration";

        authorizationService.save(User);
        return "redirect:/auth/login";
    }

}
