package com.YouCode.ebanky.config;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Bean
    public Keycloak keycloak() {
        return Keycloak.getInstance(
                keycloakServerUrl,
                "master",
                "admin",
                "admin",
                "admin-cli"
        );
    }
}