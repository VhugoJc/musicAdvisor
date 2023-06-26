package com.hyperskill.musicAdvisor.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;
import com.hyperskill.musicAdvisor.auth.AuthService;
import com.hyperskill.musicAdvisor.utils.Variables;
import com.hyperskill.musicAdvisor.views.SpotifyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpotifyController implements StreamingController{
    @Autowired
    AuthService authService;
    @Autowired
    SpotifyView view;

    // Utility method to retrieve JSON data from a specified URL with an optional limit.
    public static JsonObject getJsonData(String url, int limit) throws IOException, InterruptedException {
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
    //Retrieves the categories from Spotify and prints them.
    public void getCategories() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return;
        }
        List<Category> categoryList = new ArrayList<>();
        // Retrieve JSON data from Spotify.
        JsonObject jsonData = getJsonData(Variables.CATEGORIES_URL.toString(),0);
        JsonObject allCategories = jsonData.getAsJsonObject("categories");

        // transform JSON to model
        allCategories.get("items").getAsJsonArray().forEach(item -> {
            if(!item.isJsonNull()){
                Category category = new Category(item.getAsJsonObject().get("id").getAsString(),item.getAsJsonObject().get("name").getAsString());
                categoryList.add(category);
            }
        });
        this.view.printCategories(categoryList);
    }

    @Override
    // Retrieves the playlists from a specific category in Spotify and prints them.
    public void getPlaylist(String categoryName) throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return;
        }
        List<Category> categoryList = new ArrayList<>();
        // // Retrieve JSON data from Spotify.
        JsonObject jsonData = getJsonData(Variables.CATEGORIES_URL.toString(),0);
        JsonObject allCategories = jsonData.getAsJsonObject("categories");

        // get ID by name
        allCategories.get("items").getAsJsonArray().forEach(item -> {
            if(!item.isJsonNull()){
                Category category = new Category(item.getAsJsonObject().get("id").getAsString(),item.getAsJsonObject().get("name").getAsString());
                categoryList.add(category);
            }
        });
        List<Playlist> playlistList = new ArrayList<>();
        List<String> id = new ArrayList<>();

        categoryList.forEach(item -> {
            if(item.getName().toLowerCase().equals(categoryName.toLowerCase() ) ){
                id.add(item.getId());
            }
        });
        if (id.size()==0){
            System.out.println("Unknown category name");
            return;
        }
        // Retrieve JSON data from Spotify.
        JsonObject jsonDataPL = getJsonData(String.format(Variables.PLAYLISTS_URL.toString(),id.get(0)),0 );
        JsonObject playLists = jsonDataPL.get("playlists").getAsJsonObject();
        // transform JSON to model
        playLists.get("items").getAsJsonArray().forEach(item->{
            if(!item.isJsonNull()){
                var song = item.getAsJsonObject();
                Playlist playlist = new Playlist(song.get("name").getAsString(),song.get("external_urls").getAsJsonObject().get("spotify").getAsString());
                playlistList.add(playlist);
            }
        });
        this.view.printPlayList(playlistList,categoryName);
    }

    @Override
    // Retrieves the new releases from Spotify and prints them.
    public void getNewReleases() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return;
        }
        List<Release> releaseList = new ArrayList<>();
        // Retrieve JSON data from Spotify.
        JsonObject jsonData = getJsonData(Variables.NEW_URL.toString().toString(),0);
        JsonObject newReleases = jsonData.getAsJsonObject("albums");

        // transform JSON to model
        newReleases.get("items").getAsJsonArray().forEach(item -> {
            if(!item.isJsonNull()){
                var album = item.getAsJsonObject();
                List<String> artists = new ArrayList<>();

                album.get("artists").getAsJsonArray().forEach(item2 -> {
                    artists.add(item2.getAsJsonObject().get("name").getAsString());
                });
                Release newRelease = new Release(album.get("name").getAsString(), artists, album.get("external_urls").getAsJsonObject().get("spotify").getAsString());
                releaseList.add(newRelease);
            }
        });
        this.view.printNewReleases(releaseList);
    }

    @Override
    // Retrieves the featured playlists from Spotify and prints them.
    public void getFeatured() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return;
        }
        // Retrieve JSON data from Spotify.
        JsonObject jsonData = getJsonData(Variables.FEATURED_URL.toString().toString(),0);
        JsonObject featured = jsonData.get("playlists").getAsJsonObject();
        // transform JSON to model
        List<Playlist> featuredList = new ArrayList<>();
        featured.get("items").getAsJsonArray().forEach(item->{
            if(!item.isJsonNull()){
                var playlist = item.getAsJsonObject();
                Playlist newPlaylist = new Playlist(playlist.get("name").getAsString(),playlist.get("external_urls").getAsJsonObject().get("spotify").getAsString());
                featuredList.add(newPlaylist);
            }
        });
        this.view.printFeatured(featuredList);
    }
}
