package com.hyperskill.musicAdvisor.controllers;
import com.hyperskill.musicAdvisor.services.AuthService;
import com.hyperskill.musicAdvisor.utils.Variables;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainController {
    @Autowired
    static
    AuthService authService;
    public static void runServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String responseBody;
            if (query != null && query.contains("code")) {
                Variables.AUTH_CODE.setUrl(query.substring(5));//code=
                responseBody = "Got the code. Return back to your program.";
                try {
                    authService.generatedAccessToken();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                responseBody = "Not found authorization code. Try again.";
            }
            exchange.sendResponseHeaders(200, "responseBody".length());
            exchange.getResponseBody().write(responseBody.getBytes());
            exchange.getResponseBody().close();
        });
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
