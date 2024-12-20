package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.Account;
import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.entities.enums.Role;
import com.YouCode.ebanky.exceptions.UserNotFoundException;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequestDTO userRequestDTO;
    private User user;
    private UserResponseDTO userResponseDTO;
    private final Long USER_ID = 1L;

    @BeforeEach
    void setUp() {
        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setFirstName("John");
        userRequestDTO.setLastName("Doe");
        userRequestDTO.setEmail("john@example.com");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setAge(30);
        userRequestDTO.setMonthlyIncome(5000.0);
        userRequestDTO.setCreditScore(750);

        user = new User();
        user.setId(USER_ID);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPassword("encodedPassword");
        user.setRole(Role.USER);
        user.setAge(30);
        user.setMonthlyIncome(5000.0);
        user.setCreditScore(750);

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(USER_ID);
        userResponseDTO.setFirstName("John");
        userResponseDTO.setLastName("Doe");
        userResponseDTO.setEmail("john@example.com");
        userResponseDTO.setRole(Role.USER);
    }

    @Test
    void createUser_Success() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(modelMapper.map(userRequestDTO, User.class)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserResponseDTO.class)).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.createUser(userRequestDTO);

        assertNotNull(result);
        assertEquals(userResponseDTO.getEmail(), result.getEmail());
        assertEquals(Role.USER, result.getRole());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getAllUsers_Success() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);
        when(modelMapper.map(any(User.class), eq(UserResponseDTO.class))).thenReturn(userResponseDTO);

        List<UserResponseDTO> result = userService.getAllUsers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void getUserById_Success() {
        Account account1 = new Account();
        account1.setBalance(1000.0);
        Account account2 = new Account();
        account2.setBalance(2000.0);
        user.setAccounts(Arrays.asList(account1, account2));

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserResponseDTO.class)).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.getUserById(USER_ID);

        assertNotNull(result);
        assertEquals(3000.0, result.getTotalSolde()); // 1000 + 2000
        verify(userRepository).findById(USER_ID);
    }

    @Test
    void getUserById_UserNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(USER_ID));
        verify(userRepository).findById(USER_ID);
    }

    @Test
    void updateUser_Success() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserResponseDTO.class)).thenReturn(userResponseDTO);
        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");

        UserResponseDTO result = userService.updateUser(USER_ID, userRequestDTO);

        assertNotNull(result);
        verify(userRepository).findById(USER_ID);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void updateUser_UserNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(USER_ID, userRequestDTO));
        verify(userRepository).findById(USER_ID);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUser_Success() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(USER_ID);

        verify(userRepository).findById(USER_ID);
        verify(userRepository).delete(user);
    }

    @Test
    void deleteUser_UserNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(USER_ID));
        verify(userRepository).findById(USER_ID);
        verify(userRepository, never()).delete(any(User.class));
    }

    @Test
    void updateUserRole_Success() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserResponseDTO.class)).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.updateUserRole(USER_ID, Role.ADMIN);

        assertNotNull(result);
        verify(userRepository).findById(USER_ID);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void updateUserRole_UserNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUserRole(USER_ID, Role.ADMIN));
        verify(userRepository).findById(USER_ID);
        verify(userRepository, never()).save(any(User.class));
    }
}