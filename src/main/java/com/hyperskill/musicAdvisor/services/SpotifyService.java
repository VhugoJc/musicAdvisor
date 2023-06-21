package com.hyperskill.musicAdvisor.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpotifyService implements StreamingPlatform{
    @Override
    public void printCategories() {
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("Top Lists");
        categoriesList.add("Pop");
        categoriesList.add("Mood");
        categoriesList.add("Latin");

        System.out.println("---CATEGORIES---");
        for(String category: categoriesList){
            System.out.println(category);
        }
    }

    @Override
    public void printPlaylist(String category) {
        List<String> playlistsList = new ArrayList<>();
        playlistsList.add("Walk Like A Badass");
        playlistsList.add("Rage Beats");
        playlistsList.add("Arab Mood Booster");

        System.out.println("---"+category.toUpperCase()+" PLAYLISTS---");
        for(String playList : playlistsList){
            System.out.println(playList);
        }
    }

    @Override
    public void printNewReleases() {
        List<String> releaseList = new ArrayList<>();
        releaseList.add("Mountains [Sia, Diplo, Labrinth]");
        releaseList.add("Runaway [Lil Peep]");
        releaseList.add("The Greatest Show [Panic! At The Disco]");
        releaseList.add("All Out Life [Slipknot]");

        System.out.println("---NEW RELEASES---");
        for(String release: releaseList){
            System.out.println(release);
        }
    }

    @Override
    public void printFeatured() {
        System.out.println("---FEATURED---");
        List<String> featuredList = new ArrayList<>();
        featuredList.add("Mellow Morning");
        featuredList.add("Wake Up and Smell the Coffee");
        featuredList.add("Monday Motivation");
        featuredList.add("Songs to Sing in the Shower");

        for(String featured : featuredList){
            System.out.println(featured);
        }
    }
}
