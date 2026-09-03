package com.medicamento.controle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityFilterChainConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/cadastro").permitAll()

                        .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}