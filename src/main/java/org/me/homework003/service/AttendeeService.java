package org.me.homework003.service;

import org.me.homework003.model.entity.Attendee;
import org.me.homework003.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees(int page, int size);

    List<Attendee> getAllAttendeesById(Long attendeeId);

    List<Attendee> createNewAttendee(AttendeeRequest request);

    List<Attendee> deleteAttendeeById(Long attendeeId);

    List<Attendee> updateAttendeeById(Long attendeeId, AttendeeRequest request);
}
