package com.hyperskill.musicAdvisor.views;

import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;

import java.io.IOException;
import java.util.List;

public interface StreamingView {
    public void printCategories(List<Category> categoriesList);
    public void printPlayList(List<Playlist> playlistList, String categoryName);
    public void printNewReleases(List<Release> releaseList);
    public void printFeatured(List<Playlist> featuredList);
}
