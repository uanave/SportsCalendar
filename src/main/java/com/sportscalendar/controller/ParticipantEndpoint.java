package com.sportscalendar.controller;

import com.sportscalendar.logic.ParticipantEditor;
import com.sportscalendar.persistence.domain.Participant;
import com.sportscalendar.persistence.domain.ParticipantDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantEndpoint {
    private final ParticipantEditor participantEditor;

    public ParticipantEndpoint(ParticipantEditor participantEditor) {
        this.participantEditor = participantEditor;
    }

    @GetMapping("/{sportId}")
    List<Participant> findAllParticipantsBySportId(@PathVariable Long sportId) {
        return participantEditor.findAllParticipantsBySportId(sportId);
    }

    @PostMapping()
    Participant saveParticipant(@RequestBody ParticipantDTO participant) {

        return participantEditor.saveParticipant(participant);
    }

    @DeleteMapping("/{participantId}")
    void deleteParticipant(@PathVariable Long participantId) {
        participantEditor.deleteParticipant(participantId);
    }
}
