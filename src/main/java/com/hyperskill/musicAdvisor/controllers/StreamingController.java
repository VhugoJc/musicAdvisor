package com.hyperskill.musicAdvisor.controllers;

import com.google.gson.JsonObject;
import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;

import java.io.IOException;
import java.util.List;

public interface StreamingController {
    public void getCategories() throws IOException, InterruptedException;
    public void getPlaylist(String category) throws IOException, InterruptedException;
    public void getNewReleases() throws IOException, InterruptedException;
    public void getFeatured() throws IOException, InterruptedException;
}
