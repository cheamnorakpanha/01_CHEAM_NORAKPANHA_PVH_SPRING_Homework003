package org.me.homework003.repository;

import org.apache.ibatis.annotations.*;
import org.me.homework003.model.entity.Venue;
import org.me.homework003.model.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    @Select("""
            SELECT * FROM venues OFFSET #{offset} LIMIT #{size}
            """)
    List<Venue> getAllVenues(int offset, int size);

    @ResultMap("venueMapper")
    @Select("""
            SELECT * FROM venues WHERE venue_id = #{venueId}
            """)
    List<Venue> getAllVenuesById(Long venueId);

    @ResultMap("venueMapper")
    @Select("""
            SELECT * FROM venues WHERE venue_id = #{venueId}
            """)
    Venue getVenueById(@Param("venueId") Long venueId);

    @Select("""
            SELECT COUNT(*) > 0 FROM venues WHERE venue_name = #{venueName}
            """)
    boolean existsByVenueName(@Param("venueName") String venueName);

    @Select("""
            SELECT COUNT(*) > 0
            FROM venues
            WHERE venue_name = #{venueName} AND venue_id != #{venueId}
            """)
    boolean existsByVenueNameAndVenueIdNot(@Param("venueName") String venueName, @Param("venueId") Long venueId);

    @ResultMap("venueMapper")
    @Select("""
            INSERT INTO venues (venue_name, location)
            VALUES (#{req.venueName}, #{req.location})
            RETURNING *
            """)
    List<Venue> createNewVenue(@Param("req") VenueRequest request);

    @ResultMap("venueMapper")
    @Select("""
            DELETE FROM venues WHERE venue_id = #{venueId} RETURNING *
            """)
    List<Venue> deleteVenueById(@Param("venueId") Long venueId);

    @ResultMap("venueMapper")
    @Select("""
            UPDATE venues
            SET venue_name = #{req.venueName},
                location = #{req.location}
            WHERE venue_id = #{venueId}
            RETURNING *
            """)
    List<Venue> updateVenueById(@Param("venueId") Long venueId, @Param("req") VenueRequest request);
}
