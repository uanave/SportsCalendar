package com.sportscalendar.persistence.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipantDTO)) return false;
        ParticipantDTO that = (ParticipantDTO) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getSportId(), that.getSportId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSportId());
    }
}
