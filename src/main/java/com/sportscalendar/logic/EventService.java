package com.sportscalendar.logic;

import com.sportscalendar.exception.EntityNotFoundException;
import com.sportscalendar.persistence.domain.Event;
import com.sportscalendar.persistence.domain.EventDTO;
import com.sportscalendar.persistence.domain.Participant;
import com.sportscalendar.persistence.domain.Sport;
import com.sportscalendar.persistence.repository.EventRepository;
import com.sportscalendar.persistence.repository.ParticipantRepository;
import com.sportscalendar.persistence.repository.SportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventEditor {
    private final SportRepository sportRepository;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    public EventEditor(SportRepository sportRepository, EventRepository eventRepository, ParticipantRepository participantRepository) {
        this.sportRepository = sportRepository;
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
    }

    public Event saveEvent(EventDTO event) {
        Optional<Sport> oSport = sportRepository.findById(event.getSportId());
        if (oSport.isEmpty()) {
            throw new EntityNotFoundException("Sport not found!");
        }
        Event newEvent = new Event(event.getDate(), oSport.get());
        return eventRepository.save(newEvent);
    }

    public Event addEventParticipant(Long eventId, Long participantId) {
        Optional<Event> oEvent = eventRepository.findById(eventId);
        if (oEvent.isEmpty()) {
            throw new EntityNotFoundException("Event not found");
        }

        Optional<Participant> oParticipant = participantRepository.findById(participantId);
        if (oParticipant.isEmpty()) {
            throw new EntityNotFoundException("Participant not found");
        }

        Event event = oEvent.get();
        event.getParticipants().add(oParticipant.get());
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Page<Event> findAllEvents(int page) {
        Pageable pagination = PageRequest.of(page, 5);
        return eventRepository.findAll(pagination);
    }

    public List<Event> findAllEventsBySport(Long sportId) {
        return eventRepository.findAllBySportId(sportId);
    }

    public Page<Event> findAllEventsBySport(Long sportId, int page) {
        Pageable pagination = PageRequest.of(page, 5);

        return eventRepository.findAllBySportId(sportId, pagination);
    }

    public Event findEvent(Long eventId) {
        Optional<Event> oEvent = eventRepository.findById(eventId);
        if (oEvent.isEmpty()) {
            throw new EntityNotFoundException("Event not found");
        }
        return oEvent.get();
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public Event deleteEventParticipant(Long eventId, Long participantId) {
        Optional<Event> oEvent = eventRepository.findById(eventId);
        if (oEvent.isEmpty()) {
            throw new EntityNotFoundException("Event not found");
        }

        Optional<Participant> oParticipant = participantRepository.findById(participantId);
        if (oParticipant.isEmpty()) {
            throw new EntityNotFoundException("Participant not found");
        }

        Event event = oEvent.get();
        event.getParticipants().remove(oParticipant.get());
        return eventRepository.save(event);
    }
}
