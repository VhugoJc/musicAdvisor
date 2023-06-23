package com.hyperskill.musicAdvisor.inputs;

import com.hyperskill.musicAdvisor.controllers.SpotifyController;
import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;
import com.hyperskill.musicAdvisor.auth.*;
import com.hyperskill.musicAdvisor.views.SpotifyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    SpotifyController controller;
    @Autowired
    SpotifyView view;
    @Autowired
    AuthService authService;


    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        while (!line.equals("exit")){
            System.out.print("> ");
            line = scanner.nextLine();
            String param = "";
            if(line.split(" ").length >= 2){
                param = line.split(" ")[1];
                line = line.split(" ")[0];
            }

            switch (line){
                case "new":
                    List<Release> newReleases = controller.getNewReleases();
                    view.printNewReleases(newReleases);
                    break;
                case "featured":
                    List<Playlist> featuredList = controller.getFeatured();
                    view.printFeatured(featuredList);
                    break;
                case "categories":
                    List<Category> categories = controller.getCategories();
                    view.printCategories(categories);
                    break;
                case "playlists":
                    List<Playlist> playlistList = controller.getPlaylist(param);
                    view.printPlayList(playlistList, param);
                    break;
                case "auth":
                    this.authService.initAuth();
                    break;
               case "exit":
                    System.out.println("---GOODBYE!---");
                    break;
                default:
                    break;
            }
        }
    }
}
