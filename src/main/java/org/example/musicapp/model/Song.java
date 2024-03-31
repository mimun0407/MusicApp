package org.example.musicapp.model;

import jakarta.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String songName;
    private String songTime;
    private String songView;
    private String songImage;
    private String songSong;
    @ManyToOne
    private Singer singer;
    @ManyToOne
    private Album album;
    @ManyToOne
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Song(int id, String songName, String songTime, String songView, String songImage, String songSong, Singer singer, Album album, Country country) {
        this.id = id;
        this.songName = songName;
        this.songTime = songTime;
        this.songView = songView;
        this.songImage = songImage;
        this.songSong = songSong;
        this.singer = singer;
        this.album = album;
        this.country = country;
    }

    public String getSongSong() {
        return songSong;
    }

    public void setSongSong(String songSong) {
        this.songSong = songSong;
    }

    public Song(int id, String songName, String songTime, String songView, String songImage, String songSong, Singer singer, Album album) {
        this.id = id;
        this.songName = songName;
        this.songTime = songTime;
        this.songView = songView;
        this.songImage = songImage;
        this.songSong = songSong;
        this.singer = singer;
        this.album = album;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Song(int id, String songName, String songTime, String songView, String songImage, String songSong) {
        this.id = id;
        this.songName = songName;
        this.songTime = songTime;
        this.songView = songView;
        this.songImage = songImage;
        this.songSong = songSong;
    }

    public Song(int id, String songName, String songTime, String songView, String songImage) {
        this.id = id;
        this.songName = songName;
        this.songTime = songTime;
        this.songView = songView;
        this.songImage = songImage;
    }

    public Song() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongTime() {
        return songTime;
    }

    public void setSongTime(String songTime) {
        this.songTime = songTime;
    }

    public String getSongView() {
        return songView;
    }

    public void setSongView(String songView) {
        this.songView = songView;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }
}
