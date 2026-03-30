package org.me.homework003.repository;

import org.apache.ibatis.annotations.*;
import org.me.homework003.model.entity.Event;
import org.me.homework003.model.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "org.me.homework003.repository.VenueRepository.getVenueById"))
    })
    @Select("""
            SELECT * FROM events OFFSET #{offset} LIMIT #{size}
            """)
    List<Event> getAllEvents(int offset, int size);

    @ResultMap("eventMapper")
    @Select("""
            SELECT * FROM events WHERE event_id = #{eventId}
            """)
    List<Event> getAllEventsById(Long eventId);

    @ResultMap("eventMapper")
    @Select("""
            INSERT INTO events (event_name, event_date, venue_id)
            VALUES (#{req.eventName}, #{req.eventDate}, #{req.venue.venueId})
            RETURNING *
            """)
    List<Event> createNewEvent(@Param("req") EventRequest request);

    @ResultMap("eventMapper")
    @Select("""
            DELETE FROM events WHERE event_id = #{eventId} RETURNING *
            """)
    List<Event> deleteEventById(@Param("eventId") Long eventId);

    @ResultMap("eventMapper")
    @Select("""
            UPDATE events
            SET event_name = #{req.eventName},
                event_date = #{req.eventDate},
                venue_id = #{req.venue.venueId}
            WHERE event_id = #{eventId}
            RETURNING *
            """)
    List<Event> updateEventById(@Param("eventId") Long eventId, @Param("req") EventRequest request);
}
