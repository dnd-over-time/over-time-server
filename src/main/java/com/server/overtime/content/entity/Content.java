package com.server.overtime.content.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.server.overtime.marker.entity.Marker;

@Entity
@Table(name = "content")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentRowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "markerRowId")
    private Marker marker;

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private String name;

    private String mediaUrl;

    private String fileExtension;
}
