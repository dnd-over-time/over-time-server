package com.server.overtime.marker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

import com.server.overtime.content.entity.Content;

@Entity
@Table(name = "marker")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markerRowId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String locationName;

    private String address;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "marker", cascade = CascadeType.ALL)
    private List<Content> contents;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
