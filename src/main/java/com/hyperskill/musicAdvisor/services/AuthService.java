package com.hyperskill.musicAdvisor.services;

import com.hyperskill.musicAdvisor.controllers.MainController;
import com.hyperskill.musicAdvisor.utils.Variables;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    MainController mainController;
    public void initAuth() throws IOException {
        MainController.MainController();
        System.out.println(
                Variables.ACCESS.toString() +
                "/authorize?client_id=" + Variables.CLIENT_ID.toString() +
                "&response_type=" + Variables.RESPONSE_TYPE.toString() +
                "&redirect_uri=" + Variables.REDIRECT_URI.toString()
        );
    }
}
