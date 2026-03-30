package org.me.homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Event name cannot be blank")
    private String eventName;

    @NotBlank(message = "Event date cannot be blank")
    private String eventDate;

    @NotNull(message = "Venue cannot be null")
    private Venue venue;

    @NotNull(message = "Attendee list cannot be null")
    private List<Attendee> attendee;
}
