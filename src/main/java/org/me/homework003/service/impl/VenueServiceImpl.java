package org.me.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.me.homework003.exception.NotFoundException;
import org.me.homework003.model.entity.Venue;
import org.me.homework003.model.request.VenueRequest;
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
        int offset = size * (page - 1);

        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public List<Venue> getAllVenuesById(Long venueId) {
        List<Venue> venues = venueRepository.getAllVenuesById(venueId);

        if (venues.isEmpty()) {
            throw new NotFoundException("Venue with id " + venueId + " not found.");
        }
        return venues;
    }

    @Override
    public List<Venue> createNewVenue(VenueRequest request) {
        return venueRepository.createNewVenue(request);
    }

    @Override
    public List<Venue> deleteVenueById(Long venueId) {
        List<Venue> deleted = venueRepository.deleteVenueById(venueId);
        if (deleted.isEmpty()) {
            throw new NotFoundException("Venue with id " + venueId + " not found.");
        }
        return deleted;
    }

    @Override
    public List<Venue> updateVenueById(Long venueId, VenueRequest request) {
        List<Venue> updated = venueRepository.updateVenueById(venueId, request);

        if (updated.isEmpty()) {
            throw new NotFoundException("Venue with id " + venueId + " not found.");
        }
        return updated;
    }
}
