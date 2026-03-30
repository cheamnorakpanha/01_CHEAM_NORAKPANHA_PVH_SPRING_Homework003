package org.me.homework003.repository;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.me.homework003.model.entity.Attendee;
import org.me.homework003.model.request.AttendeeRequest;
import org.me.homework003.model.request.UpdateAttendeeRequest;

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

    @Select("""
            SELECT COUNT(*) > 0 FROM attendees WHERE email = #{email}
            """)
    boolean existsByEmail(@Param("email") String email);

    @Select("""
            SELECT COUNT(*) > 0 FROM attendees WHERE attendee_name = #{attendeeName}
            """)
    boolean existsByAttendeeName(@Param("attendeeName") String attendeeName);

    @Select("""
            SELECT COUNT(*) > 0
            FROM attendees
            WHERE email = #{email} AND attendee_id != #{attendeeId}
            """)
    boolean existsByEmailAndAttendeeIdNot(@Param("email") String email, @Param("attendeeId") Long attendeeId);

    @Select("""
            SELECT COUNT(*) > 0
            FROM attendees
            WHERE attendee_name = #{attendeeName} AND attendee_id != #{attendeeId}
            """)
    boolean existsByAttendeeNameAndAttendeeIdNot(@Param("attendeeName") String attendeeName, @Param("attendeeId") Long attendeeId);

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
            SET attendee_name = #{req.attendeeName}
            WHERE attendee_id = #{attendeeId}
            RETURNING *
            """)
    List<Attendee> updateAttendeeById(@Param("attendeeId") Long attendeeId, @Param("req") UpdateAttendeeRequest request);
}
