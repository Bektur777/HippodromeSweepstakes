package kg.bektur.hippodromesweepstakes.controllers;

import kg.bektur.hippodromesweepstakes.dto.BetDto;
import kg.bektur.hippodromesweepstakes.services.AuthorizationService;
import kg.bektur.hippodromesweepstakes.services.BetService;
import kg.bektur.hippodromesweepstakes.services.HorseService;
import kg.bektur.hippodromesweepstakes.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class MainController {

    private final AuthorizationService authorizationService;

    private final RaceService raceService;

    private final HorseService horseService;

    private final BetService betService;

    @Autowired
    public MainController(AuthorizationService authorizationService, RaceService raceService, HorseService horseService, BetService betService) {
        this.authorizationService = authorizationService;
        this.raceService = raceService;
        this.horseService = horseService;
        this.betService = betService;
    }

//    @GetMapping
//    public String showCurrentUser(Model model) {
//        model.addAttribute("user", authorizationService.getCurrentUser());
//        return "index";
//    }

    @GetMapping("/")
    public String showAllRaces(Model model) {
        model.addAttribute("races", raceService.findAllRaces())
            .addAttribute("user", authorizationService.getCurrentUser());
        return "user/races";
    }

    @GetMapping("/races/{id}")
    public String showRaceById(@PathVariable("id") Long id, @ModelAttribute("bet") BetDto betDto, Model model) {
        model
                .addAttribute("user", authorizationService.getCurrentUser())
                .addAttribute("race", raceService.findRaceById(id));
        return "user/detail_race";
    }

    @GetMapping("/bet/{horse_id}")
    public String showFormToBetOnRace(@PathVariable("horse_id") Long horse_id, @ModelAttribute("bet") BetDto betDto, Model model) {
        model.addAttribute("betHorse", horseService.findHorseById(horse_id));
        return "user/bet";
    }

    @PostMapping("/bet/{horse_id}")
    public String saveBet(@ModelAttribute("bet") BetDto betDto, @PathVariable("horse_id") Long horse_id) {
        betService.save(horse_id, betDto);
        return "redirect:/";
    }

}
