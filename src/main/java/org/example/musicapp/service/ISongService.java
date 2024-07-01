package org.example.musicapp.service;

import org.example.musicapp.dto.PaginateRequest;
import org.example.musicapp.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ISongService {
    void create (Song song);
    Optional<Song> viewBySongId(int id);
    Page<Song> viewAllSong(PaginateRequest pageable, Song song);
    void deleteSong(int id);
    void update(Song song);
}
