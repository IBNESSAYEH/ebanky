package com.YouCode.ebanky.services.implementation;


import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.mappers.UserMapper;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        user.setEncryptedPassword("aaaaa");
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        double totalSolde = user.getAccounts() == null ? 0.0 :
                user.getAccounts().stream()
                        .mapToDouble(Account::getBalance)
                        .sum();
        UserResponseDTO userResponseDTO =  userMapper.toResponseDTO(user);
        userResponseDTO.setTotalSolde(totalSolde);
        return userResponseDTO;
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setAge(userRequestDTO.getAge());

        user.setMonthlyIncome(userRequestDTO.getMonthlyIncome());
        user.setCreditScore(userRequestDTO.getCreditScore());
        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}