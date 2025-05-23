package com.server.overtime.content.controller;

import com.server.overtime.content.dto.ContentResponse;
import com.server.overtime.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/markers/{markerRowId}/contents")
    public List<ContentResponse> getContentsByMarkerId(@PathVariable Long markerRowId) {
        return contentService.getContentsByMarkerId(markerRowId);
    }

    @GetMapping("/contents/{contentId}")
    public ContentResponse getContentById(@PathVariable Long contentId) {
        return contentService.getContentById(contentId);
    }
}
