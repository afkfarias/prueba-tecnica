package com.afarias.prueba_tecnica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/equipos/**")
                    .permitAll()
                    .requestMatchers("/h2-console/**")
                    .permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .csrf(csrf -> csrf.disable())
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
  }
}
