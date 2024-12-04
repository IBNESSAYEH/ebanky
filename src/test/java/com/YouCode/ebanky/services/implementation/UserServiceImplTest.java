//package com.YouCode.ebanky.services.implementation;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.YouCode.ebanky.entities.Account;
//import com.YouCode.ebanky.entities.User;
//import com.YouCode.ebanky.repositories.UserRepository;
//import com.YouCode.ebanky.shared.dtos.requests.UserRequestDTO;
//import com.YouCode.ebanky.shared.dtos.responses.UserResponseDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private UserMapper userMapper;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createUser_shouldReturnUserResponseDTO() {
//
//        UserRequestDTO userRequestDTO = new UserRequestDTO();
//           User user = new User();
//        user.setEncryptedPassword("aaaaa");
//         User savedUser = new User();
//        savedUser.setId(1L);
//
//        UserResponseDTO expectedResponse = new UserResponseDTO();
//
//          when(userMapper.toEntity(userRequestDTO)).thenReturn(user);
//        when(userRepository.save(user)).thenReturn(savedUser);
//when(userMapper.toResponseDTO(savedUser)).thenReturn(expectedResponse);
//
//        UserResponseDTO actualResponse = userService.createUser(userRequestDTO);
//
//        assertNotNull(actualResponse);
//        verify(userRepository, times(1)).save(user);
//        verify(userMapper, times(1)).toEntity(userRequestDTO);
//        verify(userMapper, times(1)).toResponseDTO(savedUser);
//    }
//
//    @Test
//    void getAllUsers_shouldReturnListOfUserResponseDTO() {
//
//        User user1 = new User();
//           User user2 = new User();
//        UserResponseDTO responseDTO1 = new UserResponseDTO();
//    UserResponseDTO responseDTO2 = new UserResponseDTO();
//
//        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
//         when(userMapper.toResponseDTO(user1)).thenReturn(responseDTO1);
//        when(userMapper.toResponseDTO(user2)).thenReturn(responseDTO2);
//
//        List<UserResponseDTO> users = userService.getAllUsers();
//
//        assertEquals(2, users.size());
//        verify(userRepository, times(1)).findAll();
//        verify(userMapper, times(1)).toResponseDTO(user1);
//        verify(userMapper, times(1)).toResponseDTO(user2);
//    }
//
////    @Test
////    void getUserById_shouldReturnUserResponseDTO() {
//
////        Long userId = 1L;
////        User user = new User();
////        user.setId(userId);
////        user.setAccounts(Arrays.asList(new Account(1L, 100.0), new Account(2L, 200.0)));
////        UserResponseDTO responseDTO = new UserResponseDTO();
////
////        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
////        when(userMapper.toResponseDTO(user)).thenReturn(responseDTO);
////
//
////        UserResponseDTO result = userService.getUserById(userId);
////
//
////        assertNotNull(result);
////        assertEquals(300.0, result.getTotalSolde());
////        verify(userRepository, times(1)).findById(userId);
////        verify(userMapper, times(1)).toResponseDTO(user);
////    }
//
//    @Test
//    void updateUser_shouldUpdateAndReturnUserResponseDTO() {
//
//        Long userId = 1L;
//        UserRequestDTO userRequestDTO = new UserRequestDTO();
//           userRequestDTO.setFirstName("UpdatedName");
//User user = new User();
//        user.setId(userId);
//
//        User updatedUser = new User();
//        UserResponseDTO responseDTO = new UserResponseDTO();
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//          when(userRepository.save(user)).thenReturn(updatedUser);
//        when(userMapper.toResponseDTO(updatedUser)).thenReturn(responseDTO);
//
//        UserResponseDTO result = userService.updateUser(userId, userRequestDTO);
//
//
//
//        assertNotNull(result);
//           assertEquals("UpdatedName", user.getFirstName());
//        verify(userRepository, times(1)).findById(userId);
//        verify(userRepository, times(1)).save(user);
//        verify(userMapper, times(1)).toResponseDTO(updatedUser);
//    }
//
//    @Test
//    void deleteUser_shouldDeleteUser() {
//
//        Long userId = 1L;
//         User user = new User();
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//userService.deleteUser(userId);
//
//          verify(userRepository, times(1)).findById(userId);
//        verify(userRepository, times(1)).delete(user);
//    }
//}
