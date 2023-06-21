package com.hyperskill.musicAdvisor.services;

public interface StreamingPlatform {
    public void printCategories();
    public void printPlaylist(String category);
    public void printNewReleases();
    public void printFeatured();
}
