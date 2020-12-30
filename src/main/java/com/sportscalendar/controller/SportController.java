package com.sportscalendar.controller;

import com.sportscalendar.logic.SportService;
import com.sportscalendar.persistence.domain.Sport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sport")
public class SportController {
    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/add")
    String add() {
        return "sport/add";
    }

    @PostMapping
    String save(@ModelAttribute Sport sport) {
        sportService.saveSport(sport);
        return "redirect:/sport";
    }

    /**
     * ToDo: add pagination to sports list
     */
    @GetMapping
    String list(Model model) {
        List<Sport> sportList = sportService.findAllSports();
        model.addAttribute(sportList);
        return "sport/list";
    }

    /**
     * ToDO: use Http DELETE method
     */
    @GetMapping("/delete/{sportId}")
    String delete(@PathVariable Long sportId) {
        sportService.deleteSport(sportId);
        return "redirect:/sport";
    }
}
