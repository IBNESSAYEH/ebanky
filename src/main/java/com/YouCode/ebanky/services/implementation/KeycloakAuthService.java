package com.YouCode.ebanky.services.implementation;


import com.YouCode.ebanky.auth.RegisterRequest;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.InvoiceResponseDTO;
import com.YouCode.ebanky.shared.dtos.responses.LoginDto;
import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.ws.rs.core.Response;
import org.springframework.transaction.annotation.Transactional;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class KeycloakAuthService {

    private final Keycloak keycloak;
    private final UserService userService;
    private final ModelMapper modelMapper;

    private final String realm = "abdellatif";
    private final String clientId = "ebanky-rest-api";
    private final String clientSecret = "vK53MTTdQ1xGWJ3CrbaTxLhZLDVlsyF3";


    @Transactional
    public UserResponseDTO register(RegisterRequest request) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(request.getEmail());
        userRepresentation.setEmail(request.getEmail());
        userRepresentation.setFirstName(request.getFirstname());
        userRepresentation.setLastName(request.getLastname());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.getPassword());
        credential.setTemporary(false);
        userRepresentation.setCredentials(Collections.singletonList(credential));

        userRepresentation.setRealmRoles(Collections.singletonList(request.getRole().name()));

        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        Response response = usersResource.create(userRepresentation);

        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            throw new RuntimeException("Failed to create user in Keycloak: " + response.readEntity(String.class));
        }

        User user = userService.save(request);

        return modelMapper.map(user ,UserResponseDTO.class);
    }

    public UserResponseDTO authenticate(UserRequestDTO request) {
        try {
            String tokenEndpoint = "http://localhost:8080/realms/" + realm + "/protocol/openid-connect/token";

            // Build the request payload
            String payload = "grant_type=" + OAuth2Constants.PASSWORD
                    + "&client_id=" + clientId
                    + "&client_secret=" + clientSecret
                    + "&username=" + request.getEmail()
                    + "&password=" + request.getPassword();

            // Send the HTTP POST request
            HttpURLConnection connection = (HttpURLConnection) new URL(tokenEndpoint).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.getOutputStream().write(payload.getBytes(StandardCharsets.UTF_8));

            // Read the response
            Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8.name());
            String responseBody = scanner.useDelimiter("\\A").next();
            scanner.close();

            // Parse the response JSON
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

            String token = (String) responseMap.get("access_token");
            long expiresIn = ((Number) responseMap.get("expires_in")).longValue();

//            User user = userService.findByEmail(request.getEmail());
//            UserDTO userDTO = userMapper.toDTO(user);

            return new LoginDto(token, expiresIn, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Authentication failed", e);
        }
    }
}
