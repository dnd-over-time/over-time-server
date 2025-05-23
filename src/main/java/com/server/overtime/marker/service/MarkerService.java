package com.server.overtime.marker.service;

import java.util.List;
import java.util.stream.Collectors;

import com.server.overtime.member.ctrl.req.AdminKey;
import org.springframework.beans.factory.annotation.Value;
import com.server.overtime.bookmark.sv.BookmarkSv;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.overtime.marker.dto.MarkerRequest;
import com.server.overtime.marker.dto.MarkerResponse;
import com.server.overtime.marker.entity.Marker;
import com.server.overtime.marker.repository.MarkerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarkerService {
    @Value("${admin.key}")
    private String adminKey;
    private final MarkerRepository markerRepository;
    private final BookmarkSv bookmarkSv;

    @Transactional
    public MarkerResponse createMarker(MarkerRequest requestDto) {
        Marker marker = Marker.builder()
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .locationName(requestDto.getLocationName())
                .address(requestDto.getAddress())
                .build();
        Marker savedMarker = markerRepository.save(marker);
        return convertToResponse(savedMarker);
    }

    @Transactional(readOnly = true)
    public List<MarkerResponse> getAllMarkers() {
        List<Marker> markers = markerRepository.findAll();

        return markers.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MarkerResponse getMarkerById(Long markerRowId) {
        Marker marker = markerRepository.findById(markerRowId)
                .orElseThrow(() -> new RuntimeException("Marker not found with id: " + markerRowId));

        return convertToResponse(marker);
    }

    @Transactional
    public void deleteMarker(Long markerRowId, AdminKey adminKey) {
        if (this.adminKey.equals(adminKey.getAdminKey())) {

            Marker marker = markerRepository.findById(markerRowId)
                    .orElseThrow(() -> new RuntimeException("Marker not found with id: " + markerRowId));
            markerRepository.delete(marker);
        }
    }

    @Transactional(readOnly = true)
    public List<MarkerResponse> getMarkerByMemberId(Long markerRowId) {
        List<Long> markerRowIdList = bookmarkSv.getMarkerRowIdList(markerRowId);
        Marker marker = markerRepository.findByIdList(markerRowIdList)
                .orElseThrow(() -> new RuntimeException("Marker not found with id: " + markerRowId));

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
