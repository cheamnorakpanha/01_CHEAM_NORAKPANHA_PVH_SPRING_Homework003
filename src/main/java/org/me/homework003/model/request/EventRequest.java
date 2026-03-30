package org.me.homework003.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank(message = "Event name cannot be blank")
    @Schema(example = "HRD Party")
    private String eventName;

    @NotBlank(message = "Event date cannot be null")
    @Schema(example = "2026-03-20", description = "Format: yyyy-MM-dd")
    private String eventDate;

    @NotNull(message = "Venue ID cannot be null")
    @Schema(example = "1073741824")
    private Long venueId;

    @NotNull(message = "Attendee IDs cannot be null")
    @Schema(example = "[1073741824]")
    private List<Long> attendeeIds;
}
