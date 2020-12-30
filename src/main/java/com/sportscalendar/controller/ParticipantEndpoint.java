package com.sportscalendar.controller;

import com.sportscalendar.logic.ParticipantService;
import com.sportscalendar.persistence.domain.Participant;
import com.sportscalendar.persistence.domain.ParticipantDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ToDo: create frontend in React
 **/
@RestController
@RequestMapping("/api/participant")
public class ParticipantEndpoint {
    private final ParticipantService participantService;

    public ParticipantEndpoint(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{sportId}")
    List<Participant> findAllParticipantsBySportId(@PathVariable Long sportId) {
        return participantService.findAllParticipantsBySportId(sportId);
    }

    @PostMapping()
    Participant saveParticipant(@RequestBody ParticipantDTO participant) {

        return participantService.saveParticipant(participant);
    }

    @DeleteMapping("/{participantId}")
    void deleteParticipant(@PathVariable Long participantId) {
        participantService.deleteParticipant(participantId);
    }

    @GetMapping
    List<Participant> findAllParticipants() {
        return participantService.findAll();
    }
}
