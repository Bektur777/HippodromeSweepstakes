package kg.bektur.hippodromesweepstakes.controllers;

import kg.bektur.hippodromesweepstakes.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    private final AuthorizationService authorizationService;

    @Autowired
    public MainController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public String showCurrentUser(Model model) {
        model.addAttribute("user", authorizationService.getCurrentUser());
        return "index";
    }

}
