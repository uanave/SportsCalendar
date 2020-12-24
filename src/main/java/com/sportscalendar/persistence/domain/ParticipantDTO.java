package com.sportscalendar.persistence.domain;

public class ParticipantDTO {
    private String name;
    private Long sportId;

    public ParticipantDTO(String name, Long sportId) {
        this.name = name;
        this.sportId = sportId;
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
