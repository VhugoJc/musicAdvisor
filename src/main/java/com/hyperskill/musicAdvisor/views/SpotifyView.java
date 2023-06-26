package com.hyperskill.musicAdvisor.views;

import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;
import com.hyperskill.musicAdvisor.utils.Variables;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementation of the StreamingView interface for displaying Spotify streaming data.
@Service
public class SpotifyView implements StreamingView{
    // Prints the current page number and total number of pages.
    public void printPage(int total){
        int page = Integer.parseInt(Variables.PAGE.toString());
        int totalPages = (int) Math.ceil((double)total / Double.parseDouble(Variables.PAGE_LIMIT.toString()));
        Variables.PAGE_SIZE.setUrl(totalPages+"");
        System.out.println("---PAGE " + page + " OF " + totalPages + "---");
    }

    // Calculates the starting index for the current page.
    public int getIndexFrom(){
        return (Integer.parseInt(Variables.PAGE.toString()) - 1) * Integer.parseInt(Variables.PAGE_LIMIT.toString());
    }

    // Calculates the ending index for the current page.
    public int getIndexTo(int total){
        return Math.min(getIndexFrom() + Integer.parseInt(Variables.PAGE_LIMIT.toString()), total);
    }
    // Prints the categories.
    @Override
    public void printCategories(List<Category> categoriesList) {
        if(categoriesList==null){
            return;
        }

        int size = categoriesList.size();
        categoriesList = categoriesList.subList(getIndexFrom(),getIndexTo(size));

        System.out.println("--- CATEGORIES ---");
        categoriesList.forEach(category->{
            System.out.println(category.getName());
        });
        this.printPage(size);
    }

    // Prints the playlists for a specific category.
    @Override
    public void printPlayList(List<Playlist> playlistList, String categoryName) {
        if(playlistList==null){
            return;
        }
        int size = playlistList.size();
        playlistList = playlistList.subList(getIndexFrom(),getIndexTo(size));

        System.out.println("---"+categoryName.toUpperCase()+" PLAYLISTS ---");
        playlistList.forEach(playlist -> {
            System.out.println(playlist.getName());
            System.out.println(playlist.getUrl());
        });
        this.printPage(size);
    }

    // Prints the new releases.
    @Override
    public void printNewReleases(List<Release> releaseList) {
        if(releaseList==null){
            return;
        }
        int size = releaseList.size();
        releaseList = releaseList.subList(getIndexFrom(),getIndexTo(size));
        System.out.println("--- NEW RELEASES ---");
        releaseList.forEach(release -> {
            System.out.println(release.getName());
            System.out.println(release.getArtists());
            System.out.println(release.getUrl());
        });
        this.printPage(size);
    }

    // Prints the featured playlists.
    @Override
    public void printFeatured(List<Playlist> featuredList) {
        if(featuredList==null){
            return;
        }
        int size = featuredList.size();
        featuredList = featuredList.subList(getIndexFrom(),getIndexTo(size));
        System.out.println("--- FEATURED ---");
        featuredList.forEach(playlist -> {
            System.out.println(playlist.getName());
            System.out.println(playlist.getUrl());
        });
        this.printPage(size);
    }
}
