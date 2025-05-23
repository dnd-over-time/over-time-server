package com.server.overtime.content.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.overtime.content.entity.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByMarkerMarkerRowId(Long markerId);
}
