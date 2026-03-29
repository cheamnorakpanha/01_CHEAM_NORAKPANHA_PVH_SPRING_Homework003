package org.me.homework003.service;

import org.apache.ibatis.annotations.Select;
import org.me.homework003.model.entity.Venue;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(int page , int size);
}
