package com.sportscalendar.controller;

import com.sportscalendar.logic.SportService;
import com.sportscalendar.persistence.domain.Sport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** ToDo: create frontend in React **/
@RestController
@RequestMapping("api/sport")
public class SportEndpoint {
    private final SportService sportService;

    public SportEndpoint(SportService sportService) {
        this.sportService = sportService;
    }

    @PostMapping
    Sport saveSport(@RequestBody Sport sport) {
        return sportService.saveSport(sport);
    }

    @GetMapping()
    List<Sport> findAllSports() {
        return sportService.findAllSports();
    }

    @DeleteMapping("/{sportId}")
    void deleteSport(@PathVariable Long sportId) {
        sportService.deleteSport(sportId);
    }
}
