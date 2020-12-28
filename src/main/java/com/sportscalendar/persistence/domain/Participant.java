package com.sportscalendar.persistence.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    @NotNull
    @JoinColumn(foreignKey = @ForeignKey(name = "PARTICIPANT_SPORT_FOREIGNKEY"), nullable = false)
    private Sport sport;

    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL)
    private List<Event> eventList;
    public Participant() {
    }

    public Participant(String name, Sport sport) {
        this.name = name;
        this.sport = sport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSport(), that.getSport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSport());
    }
}
