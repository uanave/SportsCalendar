package com.sportscalendar.persistence.domain;

import com.sun.istack.NotNull;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private List<Participant> participantList;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private List<Event> eventList;

    public Sport() {
    }

    public Sport(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sport(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sport)) return false;
        Sport sport = (Sport) o;
        return Objects.equals(getId(), sport.getId()) &&
                Objects.equals(getName(), sport.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
