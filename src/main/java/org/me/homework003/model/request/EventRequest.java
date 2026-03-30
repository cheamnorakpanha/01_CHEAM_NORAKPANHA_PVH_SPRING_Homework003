package org.me.homework003.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.me.homework003.model.entity.Attendee;
import org.me.homework003.model.entity.Venue;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String eventName;
    private String eventDate;
    private Venue venue;
    private List<Attendee> attendee;
}
