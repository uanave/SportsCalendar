package com.sportscalendar.logic;

import com.sportscalendar.persistence.domain.Participant;
import com.sportscalendar.persistence.domain.ParticipantDTO;
import com.sportscalendar.persistence.domain.Sport;
import com.sportscalendar.persistence.repository.ParticipantRepository;
import com.sportscalendar.persistence.repository.SportRepository;
import com.sportscalendar.websecurity.SecurityConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class ParticipantServiceTest {

    @Autowired
    ParticipantService participantService;

    @MockBean
    ParticipantRepository participantRepository;

    @MockBean
    SportRepository sportRepository;

    @MockBean
    SecurityConfiguration securityConfiguration;

    Long sportId = 1L;
    Long participantId = 2L;
    Sport sport = new Sport(1L, "Test");
    Participant participant = new Participant("Test", sport);
    ParticipantDTO participantDTO = new ParticipantDTO("Test", sportId);
    List<Participant> participantList = List.of(participant);


    @Test
    void findAllParticipantsBySportId() {
        when(participantRepository.findAllBySportId(sportId)).thenReturn(participantList);
        List<Participant> participantsFound = participantRepository.findAllBySportId(sportId);
        assertEquals(participantsFound, participantList);
        assertFalse(participantsFound.isEmpty());
        verify(participantRepository).findAllBySportId(sportId);
    }

    @Test
    void saveParticipantExistingSport() {
        when(sportRepository.findById(sportId)).thenReturn(Optional.of(sport));
        Optional<Sport> oSport = sportRepository.findById(sportId);
        Assertions.assertNotNull(oSport);
        Participant participantNew = new Participant(participantDTO.getName(), sport);
        participantRepository.save(participantNew);
        verify(sportRepository).findById(sportId);
        verify(participantRepository).save(participantNew);
    }

    @Test
    void saveParticipantNonExistingSport() {
        when(sportRepository.findById(sportId)).thenReturn(Optional.empty());
        Optional<Sport> oSport = sportRepository.findById(sportId);
        assertTrue(oSport.isEmpty());
        verify(sportRepository).findById(sportId);
        verifyNoMoreInteractions(participantRepository);
    }

    @Test
    void deleteParticipant() {
        when(participantRepository.findById(participantId)).thenReturn(Optional.of(participant));
        participantRepository.deleteById(participantId);
        assertFalse(participantRepository.existsById(participantId));
        verify(participantRepository).deleteById(participantId);
    }

    @Test
    void findAll() {
        participantRepository.findAll();
        verify(participantRepository).findAll();
    }
}