package com.ecommers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class appconfig {




//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails= User.builder().username("dinesh").password(passwordEncoder().encode("dinesh")).roles("ADMIN").build();
//        UserDetails userDetails1= User.builder().username("kumar").password(passwordEncoder().encode("dinesj")).roles(("ADMIN")).build();
//        return new InMemoryUserDetailsManager(userDetails,userDetails1);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
