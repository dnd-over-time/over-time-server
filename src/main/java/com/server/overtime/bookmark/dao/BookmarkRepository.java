package com.server.overtime.bookmark.dao;

import com.server.overtime.bookmark.entity.Bookmark;
import com.server.overtime.marker.entity.Marker;
import com.server.overtime.member.dao.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select b from Bookmark b where b.memberRowId = :memberRowId")
    List<Bookmark> findByMemberRowId(@Param("memberRowId")Long memberRowId);
}
