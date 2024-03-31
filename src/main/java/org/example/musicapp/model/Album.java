package org.example.musicapp.model;

import jakarta.persistence.*;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumId;
    private String albumName;
    private String albumDay;
    @ManyToOne
    private Singer singer;
    private String albumImage;


    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumDay() {
        return albumDay;
    }

    public void setAlbumDay(String albumDay) {
        this.albumDay = albumDay;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public Album() {
    }

    public Album(int albumId, String albumName, String albumDay, Singer singer, String albumImage) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumDay = albumDay;
        this.singer = singer;
        this.albumImage = albumImage;
    }
}
