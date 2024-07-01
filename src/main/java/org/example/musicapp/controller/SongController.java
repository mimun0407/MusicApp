package org.example.musicapp.controller;


import org.example.musicapp.dto.PaginateRequest;
import org.example.musicapp.model.Album;
import org.example.musicapp.model.Keyword;
import org.example.musicapp.model.Singer;
import org.example.musicapp.model.Song;

import org.example.musicapp.repository.SongRepo;
import org.example.musicapp.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/song")
public class SongController {
    @Autowired
    ISongService songService;
    @Autowired
    SongRepo songRepo;
    public static String UPLOAD_DIRECTORY = "/home/dang/Test111111111111111111/MusicApp/src/main/resources/static/freeFile/";
    public String uploadImage(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(UPLOAD_DIRECTORY+fileName));
        return fileName;
    }
    @GetMapping("/createSongF")
    public ModelAndView createSongF(){
        ModelAndView modelAndView=new ModelAndView("createSongF");
        modelAndView.addObject("newSong",new Song());
        return modelAndView;
    }
    @PostMapping("/createSong")
    public ModelAndView createSong( @RequestParam(value = "image") MultipartFile image, @RequestParam(value = "music")MultipartFile music, Song song) throws IOException {
        ModelAndView modelAndView;
        if (song.getSongName().isEmpty()){
            modelAndView=new ModelAndView("createSongF");
            modelAndView.addObject("newSong",new Song());
            modelAndView.addObject("msg","please enter song name");
return modelAndView;
        }
        modelAndView=new ModelAndView("redirect:/song/findSong");
        song.setSongImage(uploadImage(image));
        song.setSongSong(uploadImage(music));
        songService.create(song);
        return modelAndView;
    }
    @GetMapping("/deleteSong/{songId}")
    public ModelAndView deleteSong(@PathVariable int songId){
        ModelAndView modelAndView=new ModelAndView("redirect:/song/findSong");
        songService.deleteSong(songId);
        return modelAndView;
    }
    @GetMapping("/viewSong/{songId}")
    public ModelAndView viewSong(@PathVariable int songId){
        ModelAndView modelAndView=new ModelAndView("viewSong");
        modelAndView.addObject("Song",songService.viewBySongId(songId).get());
        return modelAndView;
    }
    @PostMapping("/updateSong")
    public  ModelAndView updateSong(Song song,@RequestParam(value = "image") MultipartFile image, @RequestParam(value = "music")MultipartFile music) throws IOException {
        ModelAndView modelAndView=new ModelAndView("viewSong");
        if (song.getSongName().isEmpty()){
            modelAndView=new ModelAndView("viewSong");
            modelAndView.addObject("Song",songService.viewBySongId(song.getId()).get());
            modelAndView.addObject("msg","please enter song name");
            return modelAndView;
        }
        song.setSongImage(uploadImage(image));
        song.setSongSong(uploadImage(music));
        songService.update(song);
        modelAndView.addObject("Song",songService.viewBySongId(song.getId()).get());
        return modelAndView;
    }
    @GetMapping("/findSong")
    public ModelAndView findBySongName(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                       @RequestParam(name = "size",required = false,defaultValue = "7") int size,
                                       @RequestParam(name = "songName",required = false)String songName,
                                       @RequestParam(name = "singerName",required = false)String singerName,
                                       @RequestParam(name = "albumName",required = false)String albumName

    ){
        Song song = new Song();
        song.setSongName(songName);
        song.setSinger(new Singer());
        song.getSinger().setSingerName(singerName);
        song.setAlbum(new Album());
        song.getAlbum().setAlbumName(albumName);
        PaginateRequest paginateRequest=new PaginateRequest(page,size);
        Page<Song> pages=songService.viewAllSong(paginateRequest,song);
        ModelAndView modelAndView=new ModelAndView("indexe");
        modelAndView.addObject("songList",pages.getContent());
        modelAndView.addObject("pageBegin", Math.max(1, page));
        modelAndView.addObject("pageEnd", Math.min(page + 2, pages.getTotalPages()));
        modelAndView.addObject("totalPages", pages.getTotalPages());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("size", size);

        modelAndView.addObject("songName",songName);
        modelAndView.addObject("singerName",singerName);
        modelAndView.addObject("albumName",albumName);
        return modelAndView;
    }

}
