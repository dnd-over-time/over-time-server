package com.server.overtime.marker.controller;

import java.util.List;

import com.server.overtime.marker.dto.MarkerRequest;
import com.server.overtime.marker.dto.MarkerResponse;
import com.server.overtime.marker.service.MarkerService;
import com.server.overtime.member.ctrl.req.AdminKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/audioguide/markers")
@RequiredArgsConstructor
public class MarkerController {

    private final MarkerService markerService;

    @PostMapping
    public ResponseEntity<MarkerResponse> createMarker(@RequestBody MarkerRequest requestDto) {
        MarkerResponse createdMarker = markerService.createMarker(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMarker);
    }

    @GetMapping
    public ResponseEntity<List<MarkerResponse>> getAllMarkers() {
        List<MarkerResponse> markers = markerService.getAllMarkers();
        return ResponseEntity.ok(markers);
    }

    @GetMapping("/{markerRowId}")
    public ResponseEntity<MarkerResponse> getMarkerById(@PathVariable Long markerRowId) {
        MarkerResponse marker = markerService.getMarkerById(markerRowId);
        return ResponseEntity.ok(marker);
    }

    @DeleteMapping("/{markerRowId}")
    public ResponseEntity<Void> deleteMarker(@PathVariable Long markerRowId, @RequestBody AdminKey adminKey) {
        markerService.deleteMarker(markerRowId, adminKey);
        return ResponseEntity.noContent().build();
    }
}
