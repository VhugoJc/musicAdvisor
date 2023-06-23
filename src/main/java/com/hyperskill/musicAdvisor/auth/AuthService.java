package com.hyperskill.musicAdvisor.auth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hyperskill.musicAdvisor.utils.Variables;
import com.sun.net.httpserver.HttpServer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CountDownLatch;

@Service
public class AuthService {
    private static final HttpClient client = HttpClient.newBuilder().build();

    public void generatedAccessToken () throws IOException, InterruptedException {
        if(Variables.AUTH_CODE.toString().equals("")){
            System.out.println("Please, provide access for application.");
            return;
        }
        System.out.println("code received");
        System.out.println("making http request for access_token...");
        HttpResponse<String> response = PostAccessTokenApi();
        if(response.statusCode()==200){
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            Variables.ACCESS_TOKEN.setUrl(json.get("access_token").getAsString());
            System.out.println("Authorized");
        }else{
            System.out.println("Something went Wrong.");
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

    public void generateAuthCode() throws IOException, InterruptedException {
        System.out.println(
                Variables.ACCESS.toString() +
                        "/authorize?client_id=" + Variables.CLIENT_ID.toString() +
                        "&response_type=" + Variables.RESPONSE_TYPE.toString() +
                        "&redirect_uri=" + Variables.REDIRECT_URI.toString()
        );
        runServer();
    }
    public boolean isAuth () throws IOException, InterruptedException {
        if(Variables.AUTH_CODE.toString().equals("") || Variables.ACCESS_TOKEN.toString().equals("")){
            return false;
        }
        return true;
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


    public void runServer() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String responseBody = "something went wrong";

            if (query != null && query.contains("code")) {
                latch.countDown();
                Variables.AUTH_CODE.setUrl(query.substring(5));//code=
                try {
                    this.generatedAccessToken();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                responseBody = "Got the code. Return back to your program.";
            } else {
                latch.countDown();
                responseBody = "Not found authorization code. Try again.";
            }
            exchange.sendResponseHeaders(200, responseBody.length());
            exchange.getResponseBody().write(responseBody.getBytes());
            exchange.getResponseBody().close();
        });
        server.start();
        latch.await();
        server.stop(10);
    }
}
