package com.sportscalendar.controller;

import com.sportscalendar.logic.EventService;
import com.sportscalendar.persistence.domain.Event;
import com.sportscalendar.persistence.domain.EventDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events-json")
public class EventEndpoint {

    private final EventService eventService;

    public EventEndpoint(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    Event saveEvent(@RequestBody EventDTO event) {
        return eventService.saveEvent(event);
    }

    @PostMapping("/{eventId}/{participantId}")
    Event addEventParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
        return eventService.addEventParticipant(eventId, participantId);
    }

    @GetMapping()
    List<Event> findAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/{sportId}")
    List<Event> findAllEventsBySport(@PathVariable Long sportId) {
        return eventService.findAllEventsBySport(sportId);
    }

    @DeleteMapping("/{eventId}")
    void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }

    @DeleteMapping("/{eventId}/{participantId}")
    Event deleteEventParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
        return eventService.deleteEventParticipant(eventId, participantId);
    }
}
