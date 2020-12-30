package com.sportscalendar.controller;

import com.sportscalendar.logic.ParticipantService;
import com.sportscalendar.logic.SportService;
import com.sportscalendar.persistence.domain.Participant;
import com.sportscalendar.persistence.domain.ParticipantDTO;
import com.sportscalendar.persistence.domain.Sport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/participant")
public class ParticipantController {
    private final ParticipantService participantService;
    private final SportService sportService;

    public ParticipantController(ParticipantService participantService, SportService sportService) {
        this.participantService = participantService;
        this.sportService = sportService;
    }

    @GetMapping("/add")
    String add(Model model) {
        List<Sport> sportList = sportService.findAllSports();
        model.addAttribute("sportList", sportList);
        return "participant/add";
    }

    @PostMapping
    String save(@ModelAttribute ParticipantDTO participantDTO, Model model) {
        Participant participant = participantService.saveParticipant(participantDTO);
        model.addAttribute("participant", participant);
        return "redirect:/participant";
    }

    /**
     * ToDo: add pagination to participants list
     */
    @GetMapping
    String findAll(Model model) {
        List<Participant> participantList = participantService.findAll();
        model.addAttribute("participantList", participantList);
        return "participant/list";
    }

    /**
     * ToDO: use Http DELETE method
     */
    @GetMapping("/delete/{participantId}")
    String delete(@PathVariable Long participantId) {
        participantService.deleteParticipant(participantId);
        return "redirect:/participant";
    }

    @GetMapping("/sport/{sportId}")
    String findAllBySportId(@PathVariable Long sportId, Model model) {
        List<Participant> participantList = participantService.findAllParticipantsBySportId(sportId);
        model.addAttribute("participantList", participantList);
        return "participant/list";
    }
}
