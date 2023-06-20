package com.hyperskill.musicAdvisor.controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MainController {
    public static void MainController() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(com.sun.net.httpserver.HttpExchange t) throws IOException {

        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
        }
    }
}


//    @GetMapping
//    public ResponseEntity<?> mainPage(@RequestParam(required = false) String code){
//        if(code!=null){
//            return new ResponseEntity<String>("Got the code. Return back to your program.",HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("Authorization code not found. Try again",HttpStatus.OK);
//    }
