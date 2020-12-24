package com.sportscalendar.persistence.domain;

import java.time.LocalDateTime;

public class EventDTO {
    private LocalDateTime date;
    private String name;
    private Long sportId;

    public EventDTO(LocalDateTime date, String name, Long sportId) {
        this.date = date;
        this.name = name;
        this.sportId = sportId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }
}
