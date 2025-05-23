package com.server.overtime.content.controller;

import com.server.overtime.content.dto.ContentRequest;
import com.server.overtime.content.dto.ContentResponse;
import com.server.overtime.content.service.ContentService;
import com.server.overtime.member.ctrl.req.AdminKey;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contents")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    @Operation(summary = "콘텐츠 생성 API")
    public ResponseEntity<ContentResponse> createContent(@RequestBody ContentRequest requestDto) {
        ContentResponse createdContent = contentService.createContent(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContent);
    }

    @GetMapping("/marker/{markerRowId}")
    @Operation(summary = "마커 ID로 콘텐츠 조회 API")
    public ResponseEntity<List<ContentResponse>> getContentsByMarkerId(@PathVariable Long markerRowId) {
        List<ContentResponse> contents = contentService.getContentsByMarkerId(markerRowId);
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/{contentId}")
    @Operation(summary = "콘텐츠 ID로 콘텐츠 조회 API")
    public ResponseEntity<ContentResponse> getContentById(@PathVariable Long contentId) {
        ContentResponse content = contentService.getContentById(contentId);
        return ResponseEntity.ok(content);
    }

    @DeleteMapping("/{contentId}")
    @Operation(summary = "콘텐츠 삭제 API")
    public ResponseEntity<Void> deleteContent(@PathVariable Long contentId, @RequestBody AdminKey adminKey) {
        contentService.deleteContent(contentId, adminKey);
        return ResponseEntity.noContent().build();
    }
}
