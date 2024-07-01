package org.example.musicapp.repository;

import org.example.musicapp.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepo extends JpaRepository<Song,Integer>, JpaSpecificationExecutor<Song> {
    @Query("SELECT s FROM Song s WHERE LOWER(s.songName) LIKE %:keyword% " +
            "AND (:sing IS NULL OR LOWER(s.singer.singerName) LIKE %:sing%) " +
            "AND (:al IS NULL OR LOWER(s.album.albumName) LIKE %:al%)")
    Page<Song> searchByKeyword(@Param("keyword") String keyword, @Param("sing") String sing, @Param("al") String al, Pageable pageable);
}
