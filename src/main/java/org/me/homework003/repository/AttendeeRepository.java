package org.me.homework003.repository;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.me.homework003.model.entity.Attendee;
import org.me.homework003.model.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select("""
            SELECT * FROM attendees OFFSET #{offset} LIMIT #{size}
            """)
    List<Attendee> getAllAttendees(int offset, int size);

    @ResultMap("attendeeMapper")
    @Select("""
            SELECT * FROM attendees WHERE attendee_id = #{attendeeId}
            """)
    List<Attendee> getAllAttendeesById(@Param("attendeeId") Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
            INSERT INTO attendees (attendee_name, email) VALUES (#{req.attendeeName}, #{req.email}) RETURNING  *
            """)
    List<Attendee> createNewAttendee(@Param("req") AttendeeRequest request);

    @ResultMap("attendeeMapper")
    @Select("""
            DELETE FROM attendees
            WHERE attendee_id = #{attendeeId}
            RETURNING *
            """)
    List<Attendee> deleteAttendeeById(@Param("attendeeId") Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
            UPDATE attendees
            SET attendee_name = #{req.attendeeName},
                email = #{req.email}
            WHERE attendee_id = #{attendeeId}
            RETURNING *
            """)
    List<Attendee> updateAttendeeById(@Param("attendeeId") Long attendeeId, @Param("req") AttendeeRequest request);
}
