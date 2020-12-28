package com.sportscalendar.persistence.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "EVENT_SPORT_FOREIGNKEY"), nullable = false)
    @NotNull
    private Sport sport;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            joinColumns =
            @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "EP_EVENT_FOREIGNKEY")),
            inverseJoinColumns =
            @JoinColumn(name = "participants_id", foreignKey = @ForeignKey(name = "EP_PARTICIPANT_FOREIGNKEY")),
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"event_id", "participants_id"})}
    )

    private Set<Participant> participants;

    public Event(LocalDateTime date, Sport sport, Set<Participant> participants) {
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

    public Long getId() {
        return id;
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

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
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
