package com.hyperskill.musicAdvisor.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewReleasesService {
    public void printNewReleases(){
        List<String> releaseList = new ArrayList<>();
        releaseList.add("Mountains [Sia, Diplo, Labrinth]");
        releaseList.add("Runaway [Lil Peep]");
        releaseList.add("The Greatest Show [Panic! At The Disco]");
        releaseList.add("All Out Life [Slipknot]");

        System.out.println("---NEW RELEASES---");
        for(String release: releaseList){
            System.out.println(release);
        }
    }
}
