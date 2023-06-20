package com.hyperskill.musicAdvisor.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeaturedService {
    public void  printFeatured(){
        System.out.println("---FEATURED---");
        List<String> featuredList = new ArrayList<>();
        featuredList.add("Mellow Morning");
        featuredList.add("Wake Up and Smell the Coffee");
        featuredList.add("Monday Motivation");
        featuredList.add("Songs to Sing in the Shower");

        for(String featured : featuredList){
            System.out.println(featured);
        }
    }
}
