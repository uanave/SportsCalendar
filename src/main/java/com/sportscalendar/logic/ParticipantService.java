package com.sportscalendar.logic;

import com.sportscalendar.exception.EntityNotFoundException;
import com.sportscalendar.persistence.domain.Participant;
import com.sportscalendar.persistence.domain.ParticipantDTO;
import com.sportscalendar.persistence.domain.Sport;
import com.sportscalendar.persistence.repository.ParticipantRepository;
import com.sportscalendar.persistence.repository.SportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final SportRepository sportRepository;

    public ParticipantService(ParticipantRepository participantRepository, SportRepository sportRepository) {
        this.participantRepository = participantRepository;
        this.sportRepository = sportRepository;
    }

    public List<Participant> findAllParticipantsBySportId(Long sportId) {
        return participantRepository.findAllBySportId(sportId);
    }

    public Participant saveParticipant(ParticipantDTO participant) {
        Optional<Sport> oSport = sportRepository.findById(participant.getSportId());
        if (oSport.isEmpty()) {
            throw new EntityNotFoundException("Sport not found!");
        }
        Participant newParticipant = new Participant(participant.getName(), oSport.get());
        return participantRepository.save(newParticipant);
    }

    public void deleteParticipant(Long participantId) {
        participantRepository.deleteById(participantId);
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }
}
