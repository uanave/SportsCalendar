package com.sportscalendar.persistence.domain;

import java.time.LocalDateTime;

public class EventDTO {

    private LocalDateTime date;
    private Long sportId;

    public EventDTO(LocalDateTime date, Long sportId) {
        this.date = date;
        this.sportId = sportId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }
}
