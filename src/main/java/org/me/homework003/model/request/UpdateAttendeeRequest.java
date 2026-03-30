package org.me.homework003.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttendeeRequest {
    @Schema(example = "Sok Sao")
    private String attendeeName;
}
