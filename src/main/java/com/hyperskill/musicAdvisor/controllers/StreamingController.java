package com.hyperskill.musicAdvisor.controllers;

import com.google.gson.JsonObject;
import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;

import java.io.IOException;
import java.util.List;

public interface StreamingController {
    public List<Category> getCategories() throws IOException, InterruptedException;
    public List<Playlist> getPlaylist(String category) throws IOException, InterruptedException;
    public List<Release> getNewReleases() throws IOException, InterruptedException;
    public List<Playlist> getFeatured() throws IOException, InterruptedException;
}
