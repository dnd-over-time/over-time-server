package com.server.overtime.content.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentResponse {
    private Long contentRowId;
    private Long markerRowId;
    private String name;
    private String description;
    private String mediaUrl;
    private String fileExtension;
}
