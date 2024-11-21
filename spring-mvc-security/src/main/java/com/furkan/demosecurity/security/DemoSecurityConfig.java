package com.furkan.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //add support for JDBC

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        //define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );
        return jdbcUserDetailsManager;
        //tell spring security to use JDBC authentication with our data source
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }

/*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {


        UserDetails ahmet = User.builder()
                .username("ahmet")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails elif = User.builder()
                .username("elif")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails kerim = User.builder()
                .username("kerim")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ahmet,elif,kerim);
    }
     */
}
