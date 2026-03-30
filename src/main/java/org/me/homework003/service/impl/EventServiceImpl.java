package org.me.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.me.homework003.model.entity.Attendee;
import org.me.homework003.model.entity.Event;
import org.me.homework003.model.request.EventRequest;
import org.me.homework003.repository.EventRepository;
import org.me.homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents(int page, int size) {
        int offset = size * (page - 1);

        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public List<Event> getAllEventsById(Long eventId) {
        return eventRepository.getAllEventsById(eventId);
    }

    @Override
    public List<Event> createNewEvent(EventRequest request) {
        return eventRepository.createNewEvent(request);
    }

    @Override
    public List<Event> deleteEventById(Long eventId) {
        return eventRepository.deleteEventById(eventId);
    }

    @Override
    public List<Event> updateEventById(Long eventId, EventRequest request) {
        return eventRepository.updateEventById(eventId, request);
    }
}
