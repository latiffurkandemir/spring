package com.furkandemir.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
//@Primary//If you mark a bean with @Primary,
// Spring will prioritize it as the default choice when no specific bean is qualified for injection.
public class TenisCoach implements Coach{

    public TenisCoach() {
        System.out.println("In constructor : " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
