package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.entities.enums.Role;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
import com.YouCode.ebanky.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        User user = modelMapper.map(userRequestDTO, User.class);
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        double totalSolde = user.getAccounts() == null ? 0.0 :
                user.getAccounts().stream()
                        .mapToDouble(Account::getBalance)
                        .sum();

        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        userResponseDTO.setTotalSolde(totalSolde);
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setAge(userRequestDTO.getAge());
        user.setMonthlyIncome(userRequestDTO.getMonthlyIncome());
        user.setCreditScore(userRequestDTO.getCreditScore());

        if (userRequestDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Transactional
    public UserResponseDTO updateUserRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        user.setRole(newRole);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponseDTO.class);
    }
}
