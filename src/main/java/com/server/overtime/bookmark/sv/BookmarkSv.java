package com.server.overtime.bookmark.sv;

import com.server.overtime.bookmark.dao.BookmarkRepository;
import com.server.overtime.bookmark.entity.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@Service
@RequiredArgsConstructor
public class BookmarkSv {
    private final BookmarkRepository bookmarkRepository;


    public List<Long> getMarkerRowIdList(Long memberRowId) {
        List<Bookmark> bookmarkList = bookmarkRepository.findByMemberRowId(memberRowId);

        return listOf();
    }

    public List<Long> getContentRowIdList(Long memberRowId) {
        List<Bookmark> bookmarkList = bookmarkRepository.findByMemberRowId(memberRowId);

        return bookmarkList.stream()
                .map(Bookmark::getContentRowId)
                .toList();
    }
}
