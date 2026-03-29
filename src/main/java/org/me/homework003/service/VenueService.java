package org.me.homework003.service;

import org.me.homework003.model.entity.Venue;
import org.me.homework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(int page , int size);

    List<Venue> getAllVenuesById(Long venueId);

    List<Venue> createNewVenue(VenueRequest request);

    List<Venue> deleteVenueById(Long venueId);

    List<Venue> updateVenueById(Long venueId, VenueRequest request);
}
