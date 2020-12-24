package com.sportscalendar.controller;

import com.sportscalendar.logic.EventEditor;
import com.sportscalendar.persistence.domain.Event;
import com.sportscalendar.persistence.domain.EventDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventEndpoint {

    private final EventEditor eventEditor;

    public EventEndpoint(EventEditor eventEditor) {
        this.eventEditor = eventEditor;
    }

    @PostMapping
    Event saveEvent(@RequestBody EventDTO event) {
        return eventEditor.saveEvent(event);
    }

    @PostMapping("/{eventId}/{participantId}")
    Event addEventParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
        return eventEditor.addEventParticipant(eventId, participantId);
    }

    @GetMapping()
    List<Event> findAllEvents() {
        return eventEditor.findAllEvents();
    }

    @GetMapping("/{sportId}")
    List<Event> findAllEventsBySport(@PathVariable Long sportId) {
        return eventEditor.findAllEventsBySport(sportId);
    }

    @DeleteMapping("/{eventId}")
    void deleteEvent(@PathVariable Long eventId) {
        eventEditor.deleteEvent(eventId);
    }

    @DeleteMapping("/{eventId}/{participantId}")
    Event deleteEventParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
        return eventEditor.deleteEventParticipant(eventId, participantId);
    }
}
