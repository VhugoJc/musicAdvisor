package com.hyperskill.musicAdvisor.models;

import java.util.List;

public class Release {
    private String name;
    private List Artists;
    private String url;

    public Release(String name, List artists, String url) {
        this.name = name;
        Artists = artists;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getArtists() {
        return Artists;
    }

    public void setArtists(List artists) {
        Artists = artists;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
