package com.server.overtime.marker.controller;

import java.util.List;

import com.server.overtime.marker.dto.MarkerResponse;
import com.server.overtime.marker.service.MarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/audioguide")
@RequiredArgsConstructor
public class MarkerController {
    // @GetMapping("/spots")
    // public List<MarkerResponse> getAudioSpots() {
    // List<MarkerResponse> audioSpots = List.of(
    // MarkerResponse.builder()
    // .markerRowId(1L)
    // .title("Spot 1")
    // .description("Description for Spot 1")
    // .name("dongmin")
    // .markerUrl("https://example.com/audio1.mp3")
    // .latitude("37.7749")
    // .longitude("-122.4194")
    // .fileExtension("mp3")
    // .build(),
    // MarkerResponse.builder()
    // .markerRowId(2L)
    // .title("Spot 2")
    // .description("Description for Spot 2")
    // .name("junwon")
    // .markerUrl("https://example.com/audio2.mp3")
    // .latitude("34.0522")
    // .longitude("-118.2437")
    // .fileExtension("mp3")
    // .build(),
    // MarkerResponse.builder()
    // .name("Spot 3")
    // .markerUrl("https://example.com/audio1.mp3")
    // .latitude("37.7749")
    // .longitude("-122.4194")
    // .fileExtension("mp3")
    // .build());
    // return audioSpots;
    // }
    private final MarkerService markerService;

    @GetMapping("/markers")
    public List<MarkerResponse> getMarkersAroundLocation() {
        return markerService.getAllMarkers();
    }

    @GetMapping("/markers/{markerRowId}")
    public MarkerResponse getMarkerById(@PathVariable Long markerRowId) {
        return markerService.getMarkerById(markerRowId);
    }
}
