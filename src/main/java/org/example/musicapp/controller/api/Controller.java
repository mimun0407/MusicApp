package org.example.musicapp.controller.api;

import org.example.musicapp.dto.PaginateRequest;
import org.example.musicapp.model.Album;
import org.example.musicapp.model.Singer;
import org.example.musicapp.model.Song;
import org.example.musicapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@RestController("apiStudentController")
@RequestMapping("/api/v1/songs")
public class Controller {

    @Autowired
    private SongService songService;

    @GetMapping()
    public ResponseEntity<Page<Song>> findBySongName(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "6") int size,
            @RequestParam(name = "songName", required = false) String songName,
            @RequestParam(name = "singerName", required = false) String singerName,
            @RequestParam(name = "albumName", required = false) String albumName
    ) {
        Song song = new Song();
        song.setSongName(songName);
        song.setSinger(new Singer());
        song.getSinger().setSingerName(singerName);
        song.setAlbum(new Album());
        song.getAlbum().setAlbumName(albumName);
        PaginateRequest paginateRequest=new PaginateRequest(page,size);
        Page<Song> songs = songService.viewAllSong(paginateRequest, song);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<Song>> findById(@PathVariable("id") int id) {
            return new ResponseEntity<>(songService.viewBySongId(id),HttpStatus.OK);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/songs/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}