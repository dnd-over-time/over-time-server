package com.server.overtime.marker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.overtime.marker.entity.Marker;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long> {

}
