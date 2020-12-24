package com.sportscalendar.controller;

import com.sportscalendar.logic.SportEditor;
import com.sportscalendar.persistence.domain.Sport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sports")
public class SportEndpoint {
    private final SportEditor sportEditor;

    public SportEndpoint(SportEditor sportEditor) {
        this.sportEditor = sportEditor;
    }

    @PostMapping
    Sport saveSport(@RequestBody Sport sport) {
        return sportEditor.saveSport(sport);
    }

    @GetMapping()
    List<Sport> findAllSports() {
        return sportEditor.findAllSports();
    }

    @DeleteMapping("/{sportId}")
    void deleteSport(@PathVariable Long sportId) {
        sportEditor.deleteSport(sportId);
    }
}
