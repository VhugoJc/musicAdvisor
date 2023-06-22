package com.hyperskill.musicAdvisor.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hyperskill.musicAdvisor.controllers.MainController;
import com.hyperskill.musicAdvisor.utils.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AuthService {
    MainController mainController;
    private static final HttpClient client = HttpClient.newBuilder().build();

    public void generatedAccessToken () throws IOException, InterruptedException {
        System.out.println("code received");
        System.out.println("making http request for access_token...");
        HttpResponse<String> response = PostAccessTokenApi();
        if(response.statusCode()==200){
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            Variables.ACCESS_TOKEN.setUrl(json.get("access_token").getAsString());
            System.out.println(Variables.ACCESS_TOKEN.toString());
            System.out.println("Authorized");
        }

    }
    private static HttpResponse<String> PostAccessTokenApi() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=" + Variables.GRANT_TYPE.toString()
                                + "&code=" + Variables.AUTH_CODE.toString()
                                + "&redirect_uri=" + Variables.REDIRECT_URI.toString()
                                + "&client_id=" + Variables.CLIENT_ID.toString()
                                + "&client_secret=" + Variables.SECRET_ID.toString()))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void generateAuthCode() throws IOException {
        MainController.runServer();
    }

    public void initAuth() throws IOException, InterruptedException {
        if(!Variables.AUTH_CODE.toString().equals("")){
            if(!Variables.ACCESS_TOKEN.toString().equals("")){
                System.out.println("Authorized"); // if it exists: AUTH_CODE & ACCESS TOKEN
            }else{
                this.generatedAccessToken(); // if it exists: AUTH_TOKEN
            }
        }else {
            this.generateAuthCode();// if it does not exist: AUTH_CODE & ACCESS TOKEN
        }
    }
}
