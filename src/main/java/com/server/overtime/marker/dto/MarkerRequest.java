package com.server.overtime.marker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarkerRequest {
    private Double latitude;
    private Double longitude;
    private String locationName;
    private String address;
}
