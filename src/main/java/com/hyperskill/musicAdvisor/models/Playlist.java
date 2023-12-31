package com.hyperskill.musicAdvisor.models;

public class Playlist {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public Playlist(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
