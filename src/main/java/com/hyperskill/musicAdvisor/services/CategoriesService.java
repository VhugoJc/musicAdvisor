package com.hyperskill.musicAdvisor.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {
    public void printCategories(){
        List<String> categoriesList = new ArrayList<>();
        categoriesList.add("Top Lists");
        categoriesList.add("Pop");
        categoriesList.add("Mood");
        categoriesList.add("Latin");

        System.out.println("---CATEGORIES---");
        for(String category: categoriesList){
            System.out.println(category);
        }
    }
}
