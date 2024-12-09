package com.YouCode.ebanky.auth;

import com.YouCode.ebanky.config.JwtService;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.entities.enums.Role;
import com.YouCode.ebanky.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .emailVerificationStatus(false)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var jwtToken = jwtService.generateToken(((UserDetails)authentication.getPrincipal()));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
