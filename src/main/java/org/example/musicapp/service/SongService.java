package org.example.musicapp.service;

import org.example.musicapp.dto.PaginateRequest;
import org.example.musicapp.dto.ProductSpecification;
import org.example.musicapp.model.Song;
import org.example.musicapp.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService {
    @Autowired
    SongRepo songRepo;

    @Override
    public void create(Song song) {
        songRepo.save(song);
    }

    @Override
    public Optional<Song> viewBySongId(int id) {
        return songRepo.findById(id);
    }

    @Override
    public Page<Song> viewAllSong(PaginateRequest paginateRequest, Song song) {
        Specification<Song> specification = new ProductSpecification(song);
        Pageable pageable= PageRequest.of(paginateRequest.getPage(), paginateRequest.getSize());
        return songRepo.findAll(specification,pageable);
    }

    @Override
    public void deleteSong(int id) {
        songRepo.deleteById(id);
    }

    @Override
    public void update(Song song) {
        songRepo.save(song);
    }
}
