package com.sportscalendar.logic;

import com.sportscalendar.persistence.domain.Sport;
import com.sportscalendar.persistence.repository.SportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportEditor {
    private final SportRepository sportRepository;

    public SportEditor(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public Sport saveSport(Sport sport) {
        return sportRepository.save(sport);
    }

    public List<Sport> findAllSports() {
        return sportRepository.findAll();
    }

    public void deleteSport(Long sportId) {
        sportRepository.deleteById(sportId);
    }
}
