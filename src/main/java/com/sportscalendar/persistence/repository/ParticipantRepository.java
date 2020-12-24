package com.sportscalendar.persistence.repository;

import com.sportscalendar.persistence.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllBySportId(Long id);
}
