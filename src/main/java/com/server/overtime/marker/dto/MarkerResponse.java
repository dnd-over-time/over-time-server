package com.server.overtime.marker.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarkerResponse {
    private Long markerRowId;
    private Double latitude;
    private Double longitude;
    private String locationName;
    private String address;
    private Integer contentCount;
    private LocalDateTime createdAt;
}
