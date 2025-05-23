package com.server.overtime.content.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.overtime.content.dto.ContentResponse;
import com.server.overtime.content.entity.Content;
import com.server.overtime.content.repository.ContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public List<ContentResponse> getContentsByMarkerId(Long markerRowId) {
        List<Content> contents = contentRepository.findByMarkerMarkerRowId(markerRowId);

        return contents.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public ContentResponse getContentById(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        return convertToResponse(content);
    }

    private ContentResponse convertToResponse(Content content) {
        return ContentResponse.builder()
                .contentRowId(content.getContentRowId())
                .markerRowId(content.getMarker().getMarkerRowId())
                .name(content.getName())
                .description(content.getDescription())
                .mediaUrl(content.getMediaUrl())
                .fileExtension(content.getFileExtension())
                .build();
    }
}
