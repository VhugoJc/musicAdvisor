package com.hyperskill.musicAdvisor.utils;

public enum Variables {
    ACCESS("https://accounts.spotify.com"),
    REDIRECT_URI("http://localhost:8080/"),
    CLIENT_ID("9da8e5b99a5b4b6ab3b1b82e042afc52"),
    SECRET_ID("9b58d4893bdf4f419aa883fa5950bf4e"),
    AUTH_CODE(""), // permission code
    RESOURCE("https://api.spotify.com"),
    ACCESS_TOKEN(""),
    RESPONSE_TYPE("code"),
    GRANT_TYPE("authorization_code"),
    NEW_URL("https://api.spotify.com/v1/browse/new-releases"),
    FEATURED_URL("https://api.spotify.com/v1/browse/featured-playlists"),
    CATEGORIES_URL("https://api.spotify.com/v1/browse/categories"),
    PLAYLISTS_URL("https://api.spotify.com/v1/browse/categories/%s/playlists"),

    PAGE("5")

    ; // API server path
    private String value;

    Variables(String value) {
        this.value = value;
    }

    public void setUrl(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
