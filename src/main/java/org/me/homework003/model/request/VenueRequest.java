package org.me.homework003.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Venue name cannot be blank")
    @Schema(example = "KSHRD DD")
    private String venueName;

    @NotBlank(message = "Location cannot be blank")
    @Schema(example = "Boeng Kok II")
    private String location;
}
