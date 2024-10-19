package com.furkandemir.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component//marked class as a spring bean(creates an instance of this class ( new CricketCoach();) bts),
// made it available for DEPENDENCY INJECTION
//@Scope("prototype")//create a new object for every injection(bean scopes)
public class CricketCoach implements Coach {


    public CricketCoach() {
        System.out.println("In constructor : " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }



//    //define init method (bean lifecycle method)
//    @PostConstruct
//    public void startUp(){
//        System.out.println("In startUp(): " + getClass().getSimpleName());
//    }
//
//    //define destroy method (bean lifecycle method)
//    @PreDestroy
//    public void cleanUp(){
//        System.out.println("In cleanUp(): " + getClass().getSimpleName());
//    }
}
