package com.furkandemir.springcoredemo.config;

import com.furkandemir.springcoredemo.common.Coach;
import com.furkandemir.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean
    public Coach swimCoach(){//bean id defaults to the method name with the lowercase
        return new SwimCoach();
    }
}
