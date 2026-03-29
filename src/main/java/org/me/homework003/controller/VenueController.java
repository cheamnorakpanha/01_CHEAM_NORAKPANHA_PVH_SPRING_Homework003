package org.me.homework003.controller;

import lombok.RequiredArgsConstructor;
import org.me.homework003.model.entity.Venue;
import org.me.homework003.service.VenueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    @GetMapping
    public List<Venue> getAllVenues(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return venueService.getAllVenues(page, size);
    }
}
