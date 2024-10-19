package com.furkandemir.springboot.demo.myspringapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {

    //inject properties

    @Value("${team.name}")
    private String teamName;

    @Value("${player.name}")
    private String playerName;

    //exposed a new endpoint for "team info"
    @GetMapping("/teaminfo")
    public String getTeamInfo() {
        return "team name : " + teamName +  " player : " + playerName;
    }

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/example")
    public String example() {
        return "example";
    }

    @GetMapping("/lucky")
    public String lucky() {
        return "lucky day";
    }


}
