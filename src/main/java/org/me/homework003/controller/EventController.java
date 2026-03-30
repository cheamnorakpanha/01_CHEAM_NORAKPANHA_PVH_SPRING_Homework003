package org.me.homework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.me.homework003.model.entity.Event;
import org.me.homework003.model.request.EventRequest;
import org.me.homework003.model.response.ApiResponse;
import org.me.homework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @Operation(summary = "Get all events")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .timestamp(Instant.now())
                .message("Retrieved events successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(eventService.getAllEvents(page, size))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get events by Id")
    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<List<Event>>> getAllEventsById(@PathVariable("event-id") Long eventId) {
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .timestamp(Instant.now())
                .message("Retrieved events successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(eventService.getAllEventsById(eventId))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Create a new event")
    @PostMapping
    public ResponseEntity<ApiResponse<List<Event>>> createNewEvent(@Valid @RequestBody EventRequest request) {
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .timestamp(Instant.now())
                .message("Retrieved events successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(eventService.createNewEvent(request))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete event by Id")
    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<List<Event>>> deleteEventById(@PathVariable("event-id") Long eventId) {
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .timestamp(Instant.now())
                .message("Retrieved events successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(eventService.deleteEventById(eventId))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update event by Id")
    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<List<Event>>> updateEventById(@PathVariable("event-id") Long eventId, @Valid @RequestBody EventRequest request) {
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .timestamp(Instant.now())
                .message("Retrieved events successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(eventService.updateEventById(eventId, request))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
