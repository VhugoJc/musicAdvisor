package com.hyperskill.musicAdvisor.controllers;

import ch.qos.logback.core.net.server.Client;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hyperskill.musicAdvisor.utils.Variables;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    private static HttpClient client;
    public static void runServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String responseBody;
            if (query != null && query.contains("code")) {
                Variables.AUTH_CODE.setUrl(query.substring(5));//code=
                responseBody = "Got the code. Return back to your program.";
            } else {
                responseBody = "Not found authorization code. Try again.";
            }
            exchange.sendResponseHeaders(200, responseBody.length());
            exchange.getResponseBody().write(responseBody.getBytes());
            exchange.getResponseBody().close();
        });
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    private HttpResponse<String> requestAccessToken () throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=" + Variables.GRANT_TYPE.toString()
                                + "&code=" + Variables.ACCESS_TOKEN
                                + "&redirect_uri=" + Variables.REDIRECT_URI.toString()
                                + "&client_id=" + Variables.AUTH_CODE.toString()
                                + "&client_secret=" + Variables.SECRET_ID.toString()))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .build();

        return client.send(req, HttpResponse.BodyHandlers.ofString());
    }
}
