package com.furkandemir.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
@SpringBootApplication(//explicitly list base packages to scan
        scanBasePackages = {"com.furkandemir.springcoredemo",
                			"com.furkandemir.util"}
)*/
@SpringBootApplication
public class SpringcoredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcoredemoApplication.class, args);
    }

}
