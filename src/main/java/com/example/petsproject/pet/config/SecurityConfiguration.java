package com.example.petsproject.pet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    // API usage
    public static final String CLIENT_USER = "client";
    public static final String CLIENT_PASSWORD = "test";
    public static final String CLIENT_ROLE = "CLIENT";

    // Manage usage
    public static final String MANAGE_USER = "management";
    public static final String MANAGE_PASSWORD = "test";
    public static final String MANAGE_ROLE = "SUPPORT";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/api/public/v1/**").hasRole(CLIENT_ROLE)
                .mvcMatchers("/manage/**").hasRole(MANAGE_ROLE)
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final PasswordEncoder passwordEncoder = passwordEncoder();
        UserDetails client = User.builder()
                .passwordEncoder(passwordEncoder::encode)
                .username(CLIENT_USER)
                .password(CLIENT_PASSWORD)
                .roles(CLIENT_ROLE).build();

        UserDetails management = User.builder()
                .passwordEncoder(passwordEncoder::encode)
                .username(MANAGE_USER)
                .password(MANAGE_PASSWORD)
                .roles(MANAGE_ROLE).build();
        return new InMemoryUserDetailsManager(client, management);
    }
}
