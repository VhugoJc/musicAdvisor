package com.hyperskill.musicAdvisor.inputs;

import com.hyperskill.musicAdvisor.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    SpotifyService spotifyService;
    @Override
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
                    this.spotifyService.printNewReleases();
                    break;
                case "featured":
                    this.spotifyService.printFeatured();
                    break;
                case "categories":
                    this.spotifyService.printCategories();
                    break;
                case "playlists":
                    this.spotifyService.printPlaylist(param);
                    break;
                case "auth":

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
