package kg.bektur.hippodromesweepstakes.controllers;

import jakarta.validation.Valid;
import kg.bektur.hippodromesweepstakes.dto.HorseDto;
import kg.bektur.hippodromesweepstakes.dto.RaceDto;
import kg.bektur.hippodromesweepstakes.dto.RaceFullDto;
import kg.bektur.hippodromesweepstakes.services.AdminService;
import kg.bektur.hippodromesweepstakes.services.HorseService;
import kg.bektur.hippodromesweepstakes.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final HorseService horseService;

    private final RaceService raceService;

    private final AdminService adminService;

    @Autowired
    public AdminController(HorseService horseService, RaceService raceService, AdminService adminService) {
        this.horseService = horseService;
        this.raceService = raceService;
        this.adminService = adminService;
    }

    @GetMapping("/horses")
    public String showAllHorses(Model model) {
        model.addAttribute("horses", horseService.findAllHorses());
        return "admin/horses";
    }

    @GetMapping("/horses/{id}")
    public String showHorseById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("horse", horseService.findHorseById(id));

        Optional<RaceFullDto> race = adminService.getHorsesRace(id);

        if (race.isPresent()) {
            model.addAttribute("horsesRace", race.get());
        } else {
            model.addAttribute("races", raceService.findAllNotReserveRaces());
        }

        return "admin/detail_horse";
    }

    @PutMapping("/horses/assign/{id}")
    public String assignRaceToHorse(@PathVariable("id") Long id, @ModelAttribute("horse") RaceFullDto raceDto) {
        adminService.assign(id, raceDto);
        return "redirect:/admin/horses/" + id;
    }

    @PutMapping("/horses/withdraw/{id}")
    public String withdrawHorseFromRace(@PathVariable("id") Long id) {
        adminService.withdrawHorse(id);
        return "redirect:/admin/horses/" + id;
    }

    @DeleteMapping("/horses/{id}")
    public String deleteHorse(@PathVariable("id") Long id) {
        horseService.delete(id);
        return "redirect:/admin/horses";
    }

    @GetMapping("/horses/create")
    public String showFormToCreateHorse(@ModelAttribute("horse") HorseDto horseDto, Model model) {
        model.addAttribute("horse", horseDto);
        return "admin/create_horse";
    }

    @PostMapping("/horses/create")
    public String createHorse(@ModelAttribute("horse") @Valid HorseDto horseDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/create_horse";

        horseService.save(horseDto);
        return "redirect:/admin/horses";
    }

   @GetMapping("/races")
   public String showAllRaces(Model model) {
        model.addAttribute("races", raceService.findAllRaces());
        return "admin/races";
   }

   @GetMapping("/races/{id}")
   public String showRaceById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("race", raceService.findRaceById(id));
        return "admin/detail_race";
   }

   @GetMapping("/races/create")
   public String showFromToCreateRace(@ModelAttribute("race") RaceDto raceDto, Model model) {
        model.addAttribute("race", raceDto);
        return "admin/create_race";
   }

}
