package com.hyperskill.musicAdvisor.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hyperskill.musicAdvisor.models.Category;
import com.hyperskill.musicAdvisor.models.Playlist;
import com.hyperskill.musicAdvisor.models.Release;
import com.hyperskill.musicAdvisor.auth.AuthService;
import com.hyperskill.musicAdvisor.utils.Variables;
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
    public List<Category> getCategories() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return null;
        }
        List<Category> categoryList = new ArrayList<>();

        JsonObject jsonData = getJsonData(Variables.CATEGORIES_URL.toString(),0);
        JsonObject allCategories = jsonData.getAsJsonObject("categories");
        allCategories.get("items").getAsJsonArray().forEach(item -> {
            Category category = new Category(item.getAsJsonObject().get("id").getAsString(),item.getAsJsonObject().get("name").getAsString());
            categoryList.add(category);
        });
        return categoryList;
    }

    @Override
    public List<Playlist> getPlaylist(String categoryName) throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return null;
        }
        List<Category> categoryList = new ArrayList<>(getCategories());
        List<Playlist> playlistList = new ArrayList<>();
        List<String> id = new ArrayList<>();

        categoryList.forEach(item -> {
            if(item.getName().toLowerCase().equals(categoryName.toLowerCase() ) ){
                id.add(item.getId());
            }
        });
        if (id.size()==0){
            System.out.println("Unknown category name");
            return null;
        }
        JsonObject jsonData = getJsonData(String.format(Variables.PLAYLISTS_URL.toString(),id.get(0)),0 );
        JsonObject playLists = jsonData.get("playlists").getAsJsonObject();

        playLists.get("items").getAsJsonArray().forEach(item->{
            var song = item.getAsJsonObject();
            Playlist playlist = new Playlist(song.get("name").getAsString(),song.get("external_urls").getAsJsonObject().get("spotify").getAsString());
            playlistList.add(playlist);
        });

        return playlistList;
    }

    @Override
    public List<Release> getNewReleases() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return null;
        }
        List<Release> releaseList = new ArrayList<>();

        JsonObject jsonData = getJsonData(Variables.NEW_URL.toString().toString(),0);
        JsonObject newReleases = jsonData.getAsJsonObject("albums");


        newReleases.get("items").getAsJsonArray().forEach(item -> {
            var album = item.getAsJsonObject();
            List<String> artists = new ArrayList<>();

            album.get("artists").getAsJsonArray().forEach(item2 -> {
                artists.add(item2.getAsJsonObject().get("name").getAsString());
            });
            Release newRelease = new Release(album.get("name").getAsString(), artists, album.get("external_urls").getAsJsonObject().get("spotify").getAsString());
            releaseList.add(newRelease);
        });
        return releaseList;
    }

    @Override
    public List<Playlist> getFeatured() throws IOException, InterruptedException {
        if(!authService.isAuth()){
            System.out.println("Please, provide access for application.");
            return null;
        }
        JsonObject jsonData = getJsonData(Variables.FEATURED_URL.toString().toString(),0);
        JsonObject featured = jsonData.get("playlists").getAsJsonObject();
        List<Playlist> featuredList = new ArrayList<>();

        featured.get("items").getAsJsonArray().forEach(item->{
            var playlist = item.getAsJsonObject();
            Playlist newPlaylist = new Playlist(playlist.get("name").getAsString(),playlist.get("external_urls").getAsJsonObject().get("spotify").getAsString());
            featuredList.add(newPlaylist);
        });
        return featuredList;
    }
}
