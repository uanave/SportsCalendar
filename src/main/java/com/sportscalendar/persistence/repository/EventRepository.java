package com.sportscalendar.persistence.repository;

import com.sportscalendar.persistence.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllBySportId(Long sportId);
}
