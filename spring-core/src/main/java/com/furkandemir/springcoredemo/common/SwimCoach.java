package com.furkandemir.springcoredemo.common;

//we are going to create config class instead of using component annotation (java config bean)
public class SwimCoach implements Coach{

    public SwimCoach() {
        System.out.println("In constructor : " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up";
    }
}
