package com.server.overtime.content.controller;

import com.server.overtime.content.dto.ContentRequest;
import com.server.overtime.content.dto.ContentResponse;
import com.server.overtime.content.service.ContentService;
import com.server.overtime.marker.dto.MarkerResponse;
import com.server.overtime.member.ctrl.req.AdminKey;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<ContentResponse> createContent(@RequestBody ContentRequest requestDto) {
        ContentResponse createdContent = contentService.createContent(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContent);
    }

    @GetMapping("/marker/{markerRowId}")
    public ResponseEntity<List<ContentResponse>> getContentsByMarkerId(@PathVariable Long markerRowId) {
        List<ContentResponse> contents = contentService.getContentsByMarkerId(markerRowId);
        return ResponseEntity.ok(contents);
    }


    @GetMapping("/{contentId}")
    public ResponseEntity<ContentResponse> getContentById(@PathVariable Long contentId) {
        ContentResponse content = contentService.getContentById(contentId);
        return ResponseEntity.ok(content);
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long contentId, @RequestBody
    AdminKey adminKey) {
        contentService.deleteContent(contentId, adminKey);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/markingList/{memberRowId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "북마킹 컨텐츠 목록 API")
    public List<ContentResponse> getMarkingList(
            @PathVariable
            @Parameter(
                    name = "memberRowId",
                    description = "조회하려는 사용자의 memberRowid",
                    required = true)
            Long memberRowId) {
        return contentService.getContentByMemberId(memberRowId);

    }
}