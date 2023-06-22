package com.hyperskill.musicAdvisor.services;

import com.google.gson.JsonObject;

import java.io.IOException;

public interface StreamingPlatform {
    public JsonObject getJsonData(String link, int limit) throws IOException, InterruptedException;
    public void printCategories() throws IOException, InterruptedException;
    public void printPlaylist(String category) throws IOException, InterruptedException;
    public void printNewReleases() throws IOException, InterruptedException;
    public void printFeatured() throws IOException, InterruptedException;
}
