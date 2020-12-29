package com.sportscalendar.controller;

import com.sportscalendar.logic.EventService;
import com.sportscalendar.logic.ParticipantService;
import com.sportscalendar.logic.SportService;
import com.sportscalendar.persistence.domain.Event;
import com.sportscalendar.persistence.domain.EventDTO;
import com.sportscalendar.persistence.domain.Participant;
import com.sportscalendar.persistence.domain.Sport;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;
    private final SportService sportService;
    private final ParticipantService participantService;

    public EventController(EventService eventService, SportService sportService, ParticipantService participantService) {
        this.eventService = eventService;
        this.sportService = sportService;
        this.participantService = participantService;
    }

    @GetMapping("/add")
    String add(Model model) {
        List<Sport> sportList = sportService.findAllSports();
        model.addAttribute(sportList);
        return "event/add";
    }

    @PostMapping
    String saveEvent(@RequestParam Long sportId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime date) {
        EventDTO eventDTO = new EventDTO(date, sportId);
        Event event = eventService.saveEvent(eventDTO);
        return "redirect:/event/" + event.getId();
    }

    @GetMapping("/{eventId}")
    String findEventBySport(@PathVariable Long eventId, Model model) {
        Event event = eventService.findEvent(eventId);
        List<Participant> participantList = participantService.findAllParticipantsBySportId(event.getSport().getId());
        model.addAttribute(event);
        model.addAttribute(participantList);
        return "event/edit";
    }

    @PostMapping("/{eventId}")
    String addParticipantToEvent(@PathVariable Long eventId, @RequestParam Long participantId) {
        eventService.addEventParticipant(eventId, participantId);
        return "redirect:/event/" + eventId;
    }

    /** ToDo: add more filters: date, date range, venue */
    @GetMapping
    String listEvents(@RequestParam(required = false) Long sportId, @RequestParam(defaultValue = "1") int page, Model model) {
        Page<Event> eventList;
        if (sportId == null || sportId == 0) {
            eventList = eventService.findAllEvents(page - 1);
        } else {
            eventList = eventService.findAllEventsBySport(sportId, page - 1);
        }
        List<Sport> sportList = sportService.findAllSports();
        model.addAttribute("selectedSport", sportId);
        model.addAttribute(sportList);
        model.addAttribute("eventList", eventList);
        int totalPages = eventList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "event/list";
    }

    //    ToDO: after DELETE return to filtered or unfiltered list, depending on where the DELETE was issued.
    @GetMapping("/delete/{eventId}")
    String deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/event";
    }

    @GetMapping("/delete/{eventId}/{participantId}")
    String deleteEventParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
        eventService.deleteEventParticipant(eventId, participantId);
        return "redirect:/event/" + eventId;
    }
}
