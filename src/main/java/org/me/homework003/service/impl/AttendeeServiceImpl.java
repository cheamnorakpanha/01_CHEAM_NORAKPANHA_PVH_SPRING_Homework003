package org.me.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.me.homework003.exception.NotFoundException;
import org.me.homework003.model.entity.Attendee;
import org.me.homework003.model.request.AttendeeRequest;
import org.me.homework003.repository.AttendeeRepository;
import org.me.homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(int page, int size) {
        int offset = size * (page - 1);

        return attendeeRepository.getAllAttendees(offset, size);
    }

    @Override
    public List<Attendee> getAllAttendeesById(Long attendeeId) {
        List<Attendee> attendee = attendeeRepository.getAllAttendeesById(attendeeId);

        if (attendee.isEmpty()) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found.");
        }
        return attendee;
    }

    @Override
    public List<Attendee> createNewAttendee(AttendeeRequest request) {
        return attendeeRepository.createNewAttendee(request);
    }

    @Override
    public List<Attendee> deleteAttendeeById(Long attendeeId) {
        List<Attendee> deleted = attendeeRepository.deleteAttendeeById(attendeeId);

        if (deleted.isEmpty()) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found.");
        }
        return deleted;
    }

    @Override
    public List<Attendee> updateAttendeeById(Long attendeeId, AttendeeRequest request) {
        List<Attendee> updated = attendeeRepository.updateAttendeeById(attendeeId, request);

        if (updated.isEmpty()) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found.");
        }
        return updated;
    }
}
