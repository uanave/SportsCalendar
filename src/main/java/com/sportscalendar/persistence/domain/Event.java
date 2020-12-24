package com.sportscalendar.persistence.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "EVENT_SPORT_FOREIGNKEY"))
    private Sport sport;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns =
            @JoinColumn(foreignKey = @ForeignKey(name = "EP_EVENT_FOREIGNKEY")),
            inverseJoinColumns =
            @JoinColumn(foreignKey = @ForeignKey(name = "EP_PARTICIPANT_FOREIGNKEY"))
    )
    private List<Participant> participants;

    public Event(LocalDateTime date, Sport sport, List<Participant> participants) {
        this.date = date;
        this.sport = sport;
        this.participants = participants;
    }

    public Event() {
    }

    public Event(LocalDateTime date, Sport sport) {
        this.date = date;
        this.sport = sport;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(getDate(), event.getDate()) &&
                Objects.equals(getSport(), event.getSport()) &&
                Objects.equals(getParticipants(), event.getParticipants());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getDate(), getSport(), getParticipants());
    }
}
