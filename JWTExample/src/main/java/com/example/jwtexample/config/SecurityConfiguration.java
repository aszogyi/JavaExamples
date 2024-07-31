package com.example.jwtexample.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableConfigurationProperties
@EnableAutoConfiguration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationConverter authenticationConverter) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/public/**").permitAll()
                                .requestMatchers("/api/private/**").authenticated()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwt -> {
                    jwt.jwtAuthenticationConverter(authenticationConverter);
                }));
        return http.build();

        /*
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/public/**").permitAll()
                                .requestMatchers("/api/private/**").authenticated()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwt -> {
                    jwt.jwtAuthenticationConverter(authenticationConverter);
                }));
        return http.build();

         */
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
