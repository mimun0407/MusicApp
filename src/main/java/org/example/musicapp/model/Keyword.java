package org.example.musicapp.model;

public class Keyword {
    private String keyword;
    private String sing;
    private String al;

    public String getSing() {
        return sing;
    }

    public void setSing(String sing) {
        this.sing = sing;
    }

    public String getAl() {
        return al;
    }

    public void setAl(String al) {
        this.al = al;
    }

    public Keyword(String keyword, String sing, String al) {
        this.keyword = keyword;
        this.sing = sing;
        this.al = al;
    }

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public Keyword() {

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
