package com.hyperskill.musicAdvisor.views;

import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyView implements StreamingView{
    @Override
    public void printCategories(List<Category> categoriesList) {
        if(categoriesList==null){
            return;
        }
        System.out.println("--- CATEGORIES ---");
        categoriesList.forEach(category->{
            System.out.println(category.getName());
        });
    }

    @Override
    public void printPlayList(List<Playlist> playlistList, String categoryName) {
        if(playlistList==null){
            return;
        }
        System.out.println("---"+categoryName.toUpperCase()+" PLAYLISTS ---");
        playlistList.forEach(playlist -> {
            System.out.println(playlist.getName());
            System.out.println(playlist.getUrl());
        });
    }

    @Override
    public void printNewReleases(List<Release> releaseList) {
        if(releaseList==null){
            return;
        }
        System.out.println("--- NEW RELEASES ---");
        releaseList.forEach(release -> {
            System.out.println(release.getName());
            System.out.println(release.getArtists());
            System.out.println(release.getUrl());
        });
    }

    @Override
    public void printFeatured(List<Playlist> featuredList) {
        if(featuredList==null){
            return;
        }
        System.out.println("--- FEATURED ---");
        featuredList.forEach(playlist -> {
            System.out.println(playlist.getName());
            System.out.println(playlist.getUrl());
        });
    }
}
