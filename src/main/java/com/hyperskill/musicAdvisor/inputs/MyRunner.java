package com.hyperskill.musicAdvisor.inputs;

import com.hyperskill.musicAdvisor.controllers.SpotifyController;
import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;
import com.hyperskill.musicAdvisor.auth.*;
import com.hyperskill.musicAdvisor.utils.Variables;
import com.hyperskill.musicAdvisor.views.SpotifyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
        String line = "";

        Scanner scanner = new Scanner(System.in);
        String opt = "";
        String param = "";

        while (!line.equals("exit")){
            System.out.print("> ");
            line = scanner.nextLine();

            if(line.split(" ").length >= 2){
                param = line.split(" ")[1];
                line = line.split(" ")[0];
            }else{
                if(!line.equals("next") && !line.equals("prev")){
                    opt = "";
                    param = "";
                }
            }
            if(!line.equals("next") && !line.equals("prev")){
                Variables.PAGE.setUrl("1");
            }


            switch (line){
                case "new":
                    opt = line;
                    controller.getNewReleases();
                    break;
                case "featured":
                    opt = line;
                    controller.getFeatured();
                    break;
                case "categories":
                    opt = line;
                    controller.getCategories();
                    break;
                case "playlists":
                    opt = line;
                    controller.getPlaylist(param);
                    break;
                case "auth":
                    this.authService.initAuth();
                    break;
               case "exit":
                    System.out.println("---GOODBYE!---");
                    break;
                case "next":
                    pageHandle(opt, true, param);
                    break;
                case "prev":
                    pageHandle(opt, false, param);
                    break;
                default:
                    break;
            }
        }
    }

    public void pageHandle(String option, boolean isNext, String param) throws IOException, InterruptedException {
        int page = Integer.parseInt(Variables.PAGE.toString());
        int page_size = Integer.parseInt(Variables.PAGE_SIZE.toString());
        if(option.equals("") || param.equals("") && option.equals("playlists")){ // if the option does not exist or the opton is playlists without params
            return;
        }
        if(isNext){
            if(page+1<=page_size){
                Variables.PAGE.setUrl((page+1)+"");
            }else{
                System.out.println("No more pages");
                return;
            }
        }else{ //previous
            if(page-1>0){
                Variables.PAGE.setUrl((page-1)+"");
            }else{
                System.out.println("No more pages");
                return;
            }
        }
        switch (option){
            case "new":
                controller.getNewReleases();
                break;
            case "featured":
                controller.getFeatured();
                break;
            case "categories":
                controller.getCategories();
                break;
            case "playlists":
                controller.getPlaylist(param);
                break;
            default:
                break;
        }
    }
}
