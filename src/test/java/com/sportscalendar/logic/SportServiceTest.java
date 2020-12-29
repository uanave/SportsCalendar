package com.sportscalendar.logic;

import com.sportscalendar.persistence.domain.Sport;
import com.sportscalendar.persistence.repository.SportRepository;
import com.sportscalendar.websecurity.SecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class SportServiceTest {

    @Autowired
    SportService sportService;

    @MockBean
    SportRepository sportRepository;

    @MockBean
    SecurityConfiguration securityConfiguration;

    Sport sport = new Sport("test");
    Sport savedSport = new Sport(1L, "test");
    Long sportId = 1L;

    @Test
    void saveSport() {
        when(sportRepository.save(sport)).thenReturn(savedSport);
        Sport result = sportService.saveSport(sport);
        assertEquals(sportId, result.getId());
        verify(sportRepository).save(sport);
    }

    @Test
    void findAllSports() {
        sportService.findAllSports();
        verify(sportRepository).findAll();
    }

    @Test
    void deleteSport() {
        when(sportRepository.findById(sportId)).thenReturn(Optional.of(savedSport));
        sportService.deleteSport(sportId);
        assertFalse(sportRepository.existsById(sportId));
        verify(sportRepository).deleteById(sportId);
    }
}