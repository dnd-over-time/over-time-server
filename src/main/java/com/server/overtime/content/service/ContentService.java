package com.server.overtime.content.service;

import java.util.List;
import java.util.stream.Collectors;

import com.server.overtime.bookmark.entity.Bookmark;
import com.server.overtime.bookmark.sv.BookmarkSv;
import com.server.overtime.member.ctrl.req.AdminKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.overtime.content.dto.ContentRequest;
import com.server.overtime.content.dto.ContentResponse;
import com.server.overtime.content.entity.Content;
import com.server.overtime.content.repository.ContentRepository;
import com.server.overtime.marker.entity.Marker;
import com.server.overtime.marker.repository.MarkerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentService {
    @Value("${admin.key}")
    private String adminKey;
    private final ContentRepository contentRepository;
    private final MarkerRepository markerRepository;
    private final BookmarkSv bookmarkSv;

    @Transactional // 추가
    public ContentResponse createContent(ContentRequest requestDto) {
        Marker marker = markerRepository.findById(requestDto.getMarkerRowId())
                .orElseThrow(() -> new RuntimeException("Marker not found with id: " + requestDto.getMarkerRowId()));

        Content content = Content.builder()
                .marker(marker)
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .mediaUrl(requestDto.getMediaUrl())
                .fileExtension(requestDto.getFileExtension())
                .build();
        Content savedContent = contentRepository.save(content);
        return convertToResponse(savedContent);
    }

    @Transactional(readOnly = true)
    public List<ContentResponse> getContentsByMarkerId(Long markerRowId) {
        if (!markerRepository.existsById(markerRowId)) {
            throw new RuntimeException("Marker not found with id: " + markerRowId + " when trying to get contents.");
        }
        List<Content> contents = contentRepository.findByMarkerMarkerRowId(markerRowId);

        return contents.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ContentResponse getContentById(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + contentId));

        return convertToResponse(content);
    }

    @Transactional
    public void deleteContent(Long contentId, AdminKey adminKey) {
        if(this.adminKey.equals(adminKey.getAdminKey())) {
            Content content = contentRepository.findById(contentId)
                    .orElseThrow(() -> new RuntimeException("Content not found with id: " + contentId));
            contentRepository.delete(content);
        }
    }

    @Transactional
    public List<ContentResponse> getContentByMemberId(Long memberRowId) {

        List<Long> contentRowIdList = bookmarkSv.getContentRowIdList(memberRowId);
        return contentRepository.findByContentRowIdIn(contentRowIdList)
                .stream().map(this::convertToResponse).toList();
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
