package org.me.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.me.homework003.model.entity.Venue;
import org.me.homework003.repository.VenueRepository;
import org.me.homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(int page, int size) {
        return venueRepository.getAllVenues(page, size);
    }
}
