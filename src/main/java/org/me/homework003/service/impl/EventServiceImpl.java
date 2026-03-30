package org.me.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.me.homework003.exception.InvalidPaginationException;
import org.me.homework003.exception.InvalidResourceIdException;
import org.me.homework003.model.entity.Event;
import org.me.homework003.model.request.EventRequest;
import org.me.homework003.repository.EventRepository;
import org.me.homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents(int page, int size) {
        Map<String, String> errors = new HashMap<>();

        if (page <= 0) {
            errors.put("page", "must be greater than 0");
        }

        if (size <= 0) {
            errors.put("size", "must be greater than 0");
        }

        if (!errors.isEmpty()) {
            throw new InvalidPaginationException(errors);
        }

        int offset = size * (page - 1);

        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public List<Event> getAllEventsById(Long eventId) {
        validateEventId(eventId);

        return eventRepository.getAllEventsById(eventId);
    }

    @Override
    public List<Event> createNewEvent(EventRequest request) {
        return eventRepository.createNewEvent(request);
    }

    @Override
    public List<Event> deleteEventById(Long eventId) {
        validateEventId(eventId);

        return eventRepository.deleteEventById(eventId);
    }

    @Override
    public List<Event> updateEventById(Long eventId, EventRequest request) {
        validateEventId(eventId);

        return eventRepository.updateEventById(eventId, request);
    }

    private void validateEventId(Long eventId) {
        if (eventId == null || eventId <= 0) {
            throw new InvalidResourceIdException(Map.of("eventId", "must be greater than 0"));
        }
    }
}
