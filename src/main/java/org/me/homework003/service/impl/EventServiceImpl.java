package org.me.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.me.homework003.exception.BadRequestException;
import org.me.homework003.exception.DuplicateResourceException;
import org.me.homework003.exception.NotFoundException;
import org.me.homework003.model.entity.Event;
import org.me.homework003.model.request.EventRequest;
import org.me.homework003.repository.AttendeeRepository;
import org.me.homework003.repository.EventRepository;
import org.me.homework003.repository.VenueRepository;
import org.me.homework003.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final AttendeeRepository attendeeRepository;

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
            throw new BadRequestException(errors);
        }

        int offset = size * (page - 1);

        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public List<Event> getAllEventsById(Long eventId) {
        validateEventId(eventId);

        List<Event> events = eventRepository.getAllEventsById(eventId);

        if (events.isEmpty()) {
            throw new NotFoundException("Event with ID " + eventId + " not found");
        }

        return events;
    }

    @Override
    @Transactional
    public List<Event> createNewEvent(EventRequest request) {
        validateEventRequest(request);
        validateVenueExists(request.getVenueId());
        validateAttendeesExist(request.getAttendeeIds());
        validateDuplicateEvent(request.getEventName(), request.getEventDate(), null);

        List<Event> created = eventRepository.createNewEvent(request);
        Long eventId = created.get(0).getEventId();

        if (request.getAttendeeIds() != null && !request.getAttendeeIds().isEmpty()) {
            eventRepository.insertEventAttendees(eventId, request.getAttendeeIds());
        }

        return created;
    }

    @Override
    public List<Event> deleteEventById(Long eventId) {
        validateEventId(eventId);

        List<Event> deleted = eventRepository.deleteEventById(eventId);

        if (deleted.isEmpty()) {
            throw new NotFoundException("Event with ID " + eventId + " not found");
        }

        return deleted;
    }

    @Override
    @Transactional
    public List<Event> updateEventById(Long eventId, EventRequest request) {
        validateEventId(eventId);
        validateEventExists(eventId);
        validateEventRequest(request);
        validateVenueExists(request.getVenueId());
        validateAttendeesExist(request.getAttendeeIds());
        validateDuplicateEvent(request.getEventName(), request.getEventDate(), eventId);

        List<Event> updated = eventRepository.updateEventById(eventId, request);
        eventRepository.deleteEventAttendeesByEventId(eventId);

        if (request.getAttendeeIds() != null && !request.getAttendeeIds().isEmpty()) {
            eventRepository.insertEventAttendees(eventId, request.getAttendeeIds());
        }

        return updated;
    }

    private void validateEventId(Long eventId) {
        if (eventId == null || eventId <= 0) {
            throw new BadRequestException(Map.of("eventId", "must be greater than 0"));
        }
    }

    private void validateEventExists(Long eventId) {
        if (eventRepository.getAllEventsById(eventId).isEmpty()) {
            throw new NotFoundException("Event with ID " + eventId + " not found");
        }
    }

    private void validateEventRequest(EventRequest request) {
        Map<String, String> errors = new HashMap<>();

        if (request.getVenueId() != null && request.getVenueId() <= 0) {
            errors.put("venueId", "Venue ID must be positive number");
        }

        if (request.getAttendeeIds() != null) {
            for (int i = 0; i < request.getAttendeeIds().size(); i++) {
                Long attendeeId = request.getAttendeeIds().get(i);
                if (attendeeId == null || attendeeId <= 0) {
                    errors.put("attendeeIds[" + i + "]", "Attendee ID must be positive number");
                }
            }
        }

        if (request.getEventDate() != null && !request.getEventDate().isBlank()) {
            try {
                LocalDate eventDate = LocalDate.parse(request.getEventDate());
                if (!eventDate.isAfter(LocalDate.now())) {
                    errors.put("eventDate", "Event date must be in the future");
                }
            } catch (DateTimeParseException e) {
                errors.put("eventDate", "Event date must be in format yyyy-MM-dd");
            }
        }

        if (!errors.isEmpty()) {
            throw new BadRequestException(errors);
        }
    }

    private void validateVenueExists(Long venueId) {
        if (venueRepository.getVenueById(venueId) == null) {
            throw new NotFoundException("Venue with ID " + venueId + " not found");
        }
    }

    private void validateAttendeesExist(List<Long> attendeeIds) {
        if (attendeeIds == null) {
            return;
        }

        for (Long attendeeId : attendeeIds) {
            if (attendeeRepository.getAllAttendeesById(attendeeId).isEmpty()) {
                throw new NotFoundException("Attendee ID with " + attendeeId + " not found");
            }
        }
    }

    private void validateDuplicateEvent(String eventName, String eventDate, Long eventId) {
        boolean duplicateEvent = eventId == null
                ? eventRepository.existsByEventNameAndEventDate(eventName, eventDate)
                : eventRepository.existsByEventNameAndEventDateAndEventIdNot(eventName, eventDate, eventId);

        if (duplicateEvent) {
            throw new DuplicateResourceException(
                    "Event name already exists on this date",
                    "http://localhost:8080/errors/duplicate-user"
            );
        }
    }
}
