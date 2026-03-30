package org.me.homework003.service;

import org.me.homework003.model.entity.Event;
import org.me.homework003.model.request.EventRequest;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents(int page, int size);

    List<Event> getAllEventsById(Long eventId);

    List<Event> createNewEvent(EventRequest request);

    List<Event> deleteEventById(Long eventId);

    List<Event> updateEventById(Long eventId, EventRequest request);
}
