package org.example.musicapp.controller;


import org.example.musicapp.model.Keyword;
import org.example.musicapp.model.Song;

import org.example.musicapp.repository.SongRepo;
import org.example.musicapp.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public static String UPLOAD_DIRECTORY = "/home/dang/Test111111111111111111/MusicApp/src/main/resources/static/";
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
        modelAndView=new ModelAndView("redirect:/song/home");
        song.setSongImage(uploadImage(image));
        song.setSongSong(uploadImage(music));
        songService.create(song);
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView showAll(@PageableDefault(value = 6) Pageable pageable){
        ModelAndView modelAndView=new ModelAndView("home");
        modelAndView.addObject("allSong",songService.viewAllSong(pageable));
        return modelAndView;
    }
    @GetMapping("/deleteSong/{songId}")
    public ModelAndView deleteSong(@PathVariable int songId){
        ModelAndView modelAndView=new ModelAndView("redirect:/song/home");
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
    public ModelAndView findBySongName(@PageableDefault(value = 6)Pageable pageable,@RequestParam("keyword") String keyword,@RequestParam("sing")String sing,@RequestParam("al") String al,Keyword SaveKey){
        ModelAndView modelAndView=new ModelAndView("home");
        modelAndView.addObject("keyword",keyword);
        modelAndView.addObject("sing",sing);
        modelAndView.addObject("al",al);
        modelAndView.addObject("allSong",songRepo.searchByKeyword(keyword,sing,al,pageable));
        return modelAndView;
    }
}
