package org.me.homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.me.homework003.exception.BadRequestException;
import org.me.homework003.exception.DuplicateResourceException;
import org.me.homework003.exception.NotFoundException;
import org.me.homework003.model.entity.Attendee;
import org.me.homework003.model.request.AttendeeRequest;
import org.me.homework003.repository.AttendeeRepository;
import org.me.homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(int page, int size) {
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

        return attendeeRepository.getAllAttendees(offset, size);
    }

    @Override
    public List<Attendee> getAllAttendeesById(Long attendeeId) {
        validateAttendeeId(attendeeId);

        List<Attendee> attendee = attendeeRepository.getAllAttendeesById(attendeeId);

        if (attendee.isEmpty()) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found.");
        }
        return attendee;
    }

    @Override
    public List<Attendee> createNewAttendee(AttendeeRequest request) {
        validateDuplicateAttendee(request.getAttendeeName(), request.getEmail(), null);

        return attendeeRepository.createNewAttendee(request);
    }

    @Override
    public List<Attendee> deleteAttendeeById(Long attendeeId) {
        validateAttendeeId(attendeeId);

        List<Attendee> deleted = attendeeRepository.deleteAttendeeById(attendeeId);

        if (deleted.isEmpty()) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found.");
        }
        return deleted;
    }

    @Override
    public List<Attendee> updateAttendeeById(Long attendeeId, AttendeeRequest request) {
        validateAttendeeId(attendeeId);
        validateAttendeeExists(attendeeId);
        validateDuplicateAttendee(request.getAttendeeName(), request.getEmail(), attendeeId);

        List<Attendee> updated = attendeeRepository.updateAttendeeById(attendeeId, request);

        if (updated.isEmpty()) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found.");
        }
        return updated;
    }

    private void validateAttendeeId(Long attendeeId) {
        if (attendeeId == null || attendeeId <= 0) {
            throw new BadRequestException(Map.of("attendeeId", "must be greater than 0"));
        }
    }

    private void validateAttendeeExists(Long attendeeId) {
        if (attendeeRepository.getAllAttendeesById(attendeeId).isEmpty()) {
            throw new NotFoundException("Attendee with id " + attendeeId + " not found.");
        }
    }

    private void validateDuplicateAttendee(String attendeeName, String email, Long attendeeId) {
        boolean duplicateName;
        boolean duplicateEmail;

        if (attendeeId == null) {
            duplicateName = attendeeRepository.existsByAttendeeName(attendeeName);
            duplicateEmail = attendeeRepository.existsByEmail(email);
        } else {
            duplicateName = attendeeRepository.existsByAttendeeNameAndAttendeeIdNot(attendeeName, attendeeId);
            duplicateEmail = attendeeRepository.existsByEmailAndAttendeeIdNot(email, attendeeId);
        }

        if (duplicateName && duplicateEmail) {
            throw new DuplicateResourceException(
                    "Attendee name and email already exist",
                    "http://localhost:8080/errors/duplicate-attendee"
            );
        }

        if (duplicateName) {
            throw new DuplicateResourceException(
                    "Attendee name already exists",
                    "http://localhost:8080/errors/duplicate-attendee"
            );
        }

        if (duplicateEmail) {
            throw new DuplicateResourceException(
                    "Email already exists",
                    "http://localhost:8080/errors/duplicate-attendee"
            );
        }
    }
}
