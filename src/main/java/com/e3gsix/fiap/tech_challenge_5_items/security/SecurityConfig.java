package com.e3gsix.fiap.tech_challenge_5_items.security;

import com.e3gsix.fiap.tech_challenge_5_items.model.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import static com.e3gsix.fiap.tech_challenge_5_items.controller.impl.ItemControllerImpl.URL_ITEM;
import static com.e3gsix.fiap.tech_challenge_5_items.controller.impl.ItemControllerImpl.URL_ITEM_BY_ID;

@Component
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String urlItemsById = URL_ITEM.concat(URL_ITEM_BY_ID);

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, URL_ITEM).hasRole(UserRole.ADMIN.getRole())
                        .requestMatchers(HttpMethod.GET, urlItemsById).permitAll()
                        .requestMatchers(HttpMethod.PATCH, urlItemsById).hasRole(UserRole.ADMIN.getRole())
                        .requestMatchers(HttpMethod.DELETE, urlItemsById).hasRole(UserRole.ADMIN.getRole())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

