package com.hyperskill.musicAdvisor.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hyperskill.musicAdvisor.utils.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpotifyService implements StreamingPlatform{
    @Autowired
    AuthService authService;

    @Override
    public JsonObject getJsonData(String url, int limit) throws IOException, InterruptedException {
        String query = limit!=0 ?"?limit=" + limit :"";
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Variables.ACCESS_TOKEN)
                .uri(URI.create(url + query))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    @Override
    public void printCategories() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return;
        }
        JsonObject jsonData = getJsonData(Variables.CATEGORIES_URL.toString(),0);
        JsonObject allCategories = jsonData.getAsJsonObject("categories");

        System.out.println("---CATEGORIES---");
        allCategories.get("items").getAsJsonArray().forEach(item -> {
            var category = item.getAsJsonObject();
            var name = category.get("name").getAsString();

            System.out.println(name);
        });

    }

    @Override
    public void printPlaylist(String category) throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return;
        }
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
    public void printNewReleases() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return;
        }
        JsonObject jsonData = getJsonData(Variables.NEW_URL.toString().toString(),0);
        JsonObject newReleases = jsonData.getAsJsonObject("albums");

        System.out.println("---NEW RELEASES---");
        newReleases.get("items").getAsJsonArray().forEach(item -> {
            var album = item.getAsJsonObject();
            System.out.println(album.get("name").getAsString());
            List<String> artists = new ArrayList<>();
            album.get("artists").getAsJsonArray().forEach(item2 -> {
                artists.add(item2.getAsJsonObject().get("name").getAsString());
            });
            System.out.println(artists);
            System.out.println(album.get("href").getAsString());

        });


    }

    @Override
    public void printFeatured() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return;
        }
        JsonObject jsonData = getJsonData(Variables.NEW_URL.toString().toString(),0);
        System.out.println(jsonData);
        System.out.println("---FEATURED---");

    }


}
