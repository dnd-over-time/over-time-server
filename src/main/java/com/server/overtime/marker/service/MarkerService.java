package com.server.overtime.marker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.overtime.marker.dto.MarkerResponse;
import com.server.overtime.marker.entity.Marker;
import com.server.overtime.marker.repository.MarkerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarkerService {
    private final MarkerRepository markerRepository;

    public List<MarkerResponse> getAllMarkers() {
        List<Marker> markers = markerRepository.findAll();

        return markers.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public MarkerResponse getMarkerById(Long markerRowId) {
        Marker marker = markerRepository.findById(markerRowId)
                .orElseThrow(() -> new RuntimeException("Marker not found"));

        return convertToResponse(marker);
    }

    private MarkerResponse convertToResponse(Marker marker) {
        return MarkerResponse.builder()
                .markerRowId(marker.getMarkerRowId())
                .latitude(marker.getLatitude())
                .longitude(marker.getLongitude())
                .locationName(marker.getLocationName())
                .address(marker.getAddress())
                .contentCount(marker.getContents() != null ? marker.getContents().size() : 0)
                .createdAt(marker.getCreatedAt())
                .build();
    }
}
