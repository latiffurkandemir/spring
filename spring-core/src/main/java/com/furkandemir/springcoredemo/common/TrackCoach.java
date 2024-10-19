package com.furkandemir.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy//creates(initialize the class) bean only when it is needed
public class TrackCoach implements Coach{

    public TrackCoach() {
        System.out.println("In constructor : " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
