package org.me.homework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.me.homework003.model.entity.Venue;
import org.me.homework003.model.request.VenueRequest;
import org.me.homework003.model.response.ApiResponse;
import org.me.homework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    @Operation(summary = "Get all venues")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .timestamp(Instant.now())
                .message("Retrieved venues successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(venueService.getAllVenues(page, size))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get venues by Id")
    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenuesById(@PathVariable("venue-id") Long venueId) {
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .timestamp(Instant.now())
                .message("Retrieved venues successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(venueService.getAllVenuesById(venueId))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Create a new venue")
    @PostMapping
    public ResponseEntity<ApiResponse<List<Venue>>> createNewVenue(@Valid @RequestBody VenueRequest request) {
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .timestamp(Instant.now())
                .message("Retrieved venues successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(venueService.createNewVenue(request))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete venue by Id")
    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<List<Venue>>> deleteVenueById(@PathVariable("venue-id") Long venueId) {
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .timestamp(Instant.now())
                .message("Retrieved venues successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(venueService.deleteVenueById(venueId))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update venue by Id")
    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<List<Venue>>> updateVenueById(@PathVariable("venue-id") Long venueId, @Valid @RequestBody VenueRequest request) {
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .timestamp(Instant.now())
                .message("Retrieved venues successfully.")
                .status(HttpStatus.OK.getReasonPhrase())
                .payload(venueService.updateVenueById(venueId, request))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
