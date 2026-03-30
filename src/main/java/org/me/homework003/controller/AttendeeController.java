package org.me.homework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.me.homework003.model.entity.Attendee;
import org.me.homework003.model.request.AttendeeRequest;
import org.me.homework003.model.response.ApiResponse;
import org.me.homework003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @Operation(summary = "Get all attendees")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .timestamp(Instant.now())
                .message("Retrieved attendees successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(attendeeService.getAllAttendees(page, size))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get attendees by Id")
    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendeesById(@PathVariable("attendee-id") Long attendeeId) {
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .timestamp(Instant.now())
                .message("Retrieved attendees successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(attendeeService.getAllAttendeesById(attendeeId))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Create a new attendee")
    @PostMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> createNewAttendee(@Valid @RequestBody AttendeeRequest request) {
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .timestamp(Instant.now())
                .message("Retrieved attendees successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(attendeeService.createNewAttendee(request))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete attendee by Id")
    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<List<Attendee>>> deleteAttendeeById(@PathVariable("attendee-id") Long attendeeId) {
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .timestamp(Instant.now())
                .message("Retrieved attendees successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(attendeeService.deleteAttendeeById(attendeeId))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update attendee by Id")
    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<List<Attendee>>> updateAttendeeById(@PathVariable("attendee-id") Long attendeeId, @Valid @RequestBody AttendeeRequest request) {
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .timestamp(Instant.now())
                .message("Retrieved attendees successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(attendeeService.updateAttendeeById(attendeeId, request))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
