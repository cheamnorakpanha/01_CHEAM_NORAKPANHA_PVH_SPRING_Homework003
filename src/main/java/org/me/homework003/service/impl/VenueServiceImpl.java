package org.me.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.me.homework003.exception.BadRequestException;
import org.me.homework003.exception.DuplicateResourceException;
import org.me.homework003.exception.NotFoundException;
import org.me.homework003.model.entity.Venue;
import org.me.homework003.model.request.VenueRequest;
import org.me.homework003.repository.VenueRepository;
import org.me.homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(int page, int size) {
        Map<String, String> errors = new HashMap<>();

        if (page <= 0) {
            errors.put("page", "must be greater than 0");
        }

        if (size <= 0) {
            errors.put("size", "must be greater than 0");
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(errors);
        }

        int offset = size * (page - 1);

        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public List<Venue> getAllVenuesById(Long venueId) {
        validateVenueId(venueId);

        List<Venue> venues = venueRepository.getAllVenuesById(venueId);

        if (venues.isEmpty()) {
            throw new NotFoundException("Venue with id " + venueId + " not found.");
        }
        return venues;
    }

    @Override
    public List<Venue> createNewVenue(VenueRequest request) {
        if (venueRepository.existsByVenueName(request.getVenueName())) {
            throw new DuplicateResourceException("Venue name already exists", "http://localhost:8080/errors/duplicate-venue");
        }

        return venueRepository.createNewVenue(request);
    }

    @Override
    public List<Venue> deleteVenueById(Long venueId) {
        validateVenueId(venueId);

        List<Venue> deleted = venueRepository.deleteVenueById(venueId);
        if (deleted.isEmpty()) {
            throw new NotFoundException("Venue with id " + venueId + " not found.");
        }
        return deleted;
    }

    @Override
    public List<Venue> updateVenueById(Long venueId, VenueRequest request) {
        validateVenueId(venueId);

        if (venueRepository.existsByVenueNameAndVenueIdNot(request.getVenueName(), venueId)) {
            throw new DuplicateResourceException("Venue name already exists", "http://localhost:8080/errors/duplicate-venue");
        }

        List<Venue> updated = venueRepository.updateVenueById(venueId, request);

        if (updated.isEmpty()) {
            throw new NotFoundException("Venue with id " + venueId + " not found.");
        }
        return updated;
    }

    private void validateVenueId(Long venueId) {
        if (venueId == null || venueId <= 0) {
            throw new BadRequestException(Map.of("venueId", "must be greater than 0"));
        }
    }
}
