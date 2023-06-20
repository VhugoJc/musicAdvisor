package com.hyperskill.musicAdvisor.inputs;

import com.hyperskill.musicAdvisor.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    NewReleasesService newReleasesService;
    @Autowired
    FeaturedService featuredService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    PlayListService playListService;
    @Autowired
    AuthService authService;

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
                    this.newReleasesService.printNewReleases();
                    break;
                case "featured":
                    this.featuredService.printFeatured();
                    break;
                case "categories":
                    this.categoriesService.printCategories();
                    break;
                case "playlists":
                    this.playListService.printPlaylist(param);
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
