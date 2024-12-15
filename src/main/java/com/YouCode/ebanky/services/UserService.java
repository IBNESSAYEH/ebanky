package com.YouCode.ebanky.services;

import com.YouCode.ebanky.auth.RegisterRequest;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    public List<UserResponseDTO> getAllUsers();

    public UserResponseDTO getUserById(Long id);

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    public void deleteUser(Long id);
    User save(RegisterRequest registerRequest);
}
