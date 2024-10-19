package com.furkandemir.springcoredemo.rest;

import com.furkandemir.springcoredemo.common.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //define a private field for dependency
    private Coach coach;

//    private Coach anotherCoach;//created for checking bean scopes

    //Constructor injection
    @Autowired //(takes instance of the parameter that has to have @Component annotation and inject it to constructor)
    public DemoController(
            @Qualifier("swimCoach") Coach theCoach) {
        System.out.println("In constructor : " + getClass().getSimpleName());
        coach = theCoach;
    }





    //Qualifier annotation
//    @Autowired
//    public DemoController(@Qualifier("cricketCoach") Coach theCoach){
//        coach=theCoach;
//    }

//    //setter injection
//    @Autowired
//    public void setCoach(Coach theCoach){
//        coach=theCoach;
//    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }


//    @GetMapping("/check")//checking bean scope
//    public String check() {
//        return "Comparing beans: coach == anotherCoach, " + (coach == anotherCoach);
//        //Check to see if this is the same bean(it can create new instance for every bean)
//        //True or False depending on the bean scope
//        //Singleton: True (one bean used for every field)
//        //Prototype: False
//    }
}
