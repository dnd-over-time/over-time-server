package com.server.overtime.marker.repository;

import com.server.overtime.member.dao.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.server.overtime.marker.entity.Marker;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Long> {

    @Query("select m from Marker m where m.markerRowId in :markerRowIdList")
    List<Marker> findByIdList(@Param("markerRowIdList")List<Long> markerRowIdList);
}
