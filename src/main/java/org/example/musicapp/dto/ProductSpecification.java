package org.example.musicapp.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.musicapp.model.Song;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification <Song> {
    private final Song song;

    public ProductSpecification(Song song) {
        this.song = song;
    }

    @Override
    public Predicate toPredicate(Root<Song> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (song.getSongName() != null && !song.getSongName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("songName"), "%" + song.getSongName() + "%"));
        }
        if (song.getSinger().getSingerName() != null && !song.getSinger().getSingerName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("singer").get("singerName"), "%" + song.getSinger().getSingerName() + "%"));
        }
        if (song.getAlbum().getAlbumName() != null && !song.getAlbum().getAlbumName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("album").get("albumName"), "%" + song.getAlbum().getAlbumName() + "%"));
        }
        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }
        return query.getRestriction();
    }
}