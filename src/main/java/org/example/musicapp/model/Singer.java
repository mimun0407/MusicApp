package org.example.musicapp.model;

import jakarta.persistence.*;

@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int singerId;
    private String singerAge;
    private String singerName;
    private String singerImage;


    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerAge() {
        return singerAge;
    }

    public void setSingerAge(String singerAge) {
        this.singerAge = singerAge;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImage() {
        return singerImage;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }

    public Singer() {
    }

    public Singer(int singerId, String singerAge, String singerName, String singerImage) {
        this.singerId = singerId;
        this.singerAge = singerAge;
        this.singerName = singerName;
        this.singerImage = singerImage;
    }
}
