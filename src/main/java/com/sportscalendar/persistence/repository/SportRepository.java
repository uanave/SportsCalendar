package com.sportscalendar.persistence.repository;

import com.sportscalendar.persistence.domain.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Long> {
}
