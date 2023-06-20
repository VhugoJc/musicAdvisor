package com.hyperskill.musicAdvisor.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayListService {
    public void printPlaylist(String category){
        List<String> playlistsList = new ArrayList<>();
        playlistsList.add("Walk Like A Badass");
        playlistsList.add("Rage Beats");
        playlistsList.add("Arab Mood Booster");

        System.out.println("---"+category.toUpperCase()+" PLAYLISTS---");
        for(String playList : playlistsList){
            System.out.println(playList);
        }
    }

}
