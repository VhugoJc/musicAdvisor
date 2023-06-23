package com.hyperskill.musicAdvisor.views;

import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;
import com.hyperskill.musicAdvisor.utils.Variables;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyView implements StreamingView{
    public void printPage(int total){
        int page = Integer.parseInt(Variables.PAGE.toString());
        int totalPages = (int) Math.ceil((double)total / Double.parseDouble(Variables.PAGE_LIMIT.toString()));
        System.out.println("---PAGE " + page + " OF " + totalPages + "---");
    }
    @Override
    public void printCategories(List<Category> categoriesList) {
        if(categoriesList==null){
            return;
        }
        System.out.println("--- CATEGORIES ---");
        categoriesList.forEach(category->{
            System.out.println(category.getName());
        });
        this.printPage(categoriesList.size());
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
        this.printPage(playlistList.size());
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
        this.printPage(releaseList.size());
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
        this.printPage(featuredList.size());
    }
}
