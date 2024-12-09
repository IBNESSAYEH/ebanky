package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
