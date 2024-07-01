package org.example.musicapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicAppApplication.class, args);
        System.out.println("\"http://localhost:8080/song/findSong");
    }

}
