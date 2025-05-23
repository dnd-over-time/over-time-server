package com.server.overtime.content.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContentRequest {
    private Long markerRowId;
    private String name;
    private String description;
    private String mediaUrl;
    private String fileExtension;
}
