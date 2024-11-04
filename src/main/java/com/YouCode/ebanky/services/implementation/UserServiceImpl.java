package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.Utils;
import com.YouCode.ebanky.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils util;

    @Override
    public UserDto createUser(UserDto userDto) {
        User checkUserByEmail = userRepository.findByEmail(userDto.getEmail());
        if (checkUserByEmail != null) throw new RuntimeException("User already exists");
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setEncryptedPassword("user Password");
        user.setUserId(util.generateStringId(32));
        User createdUser = userRepository.save(user);
        UserDto createdUserDto = new UserDto();
        BeanUtils.copyProperties(createdUser, createdUserDto);

        return createdUserDto;
    }

    @Override
    public UserDto findUserByUserId(String userId){
        User user = userRepository.findByUserId(userId);
        if(user == null) throw new RuntimeException("User not found");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = userRepository.findByUserId(userId);
        if(user == null) throw new RuntimeException("User not found");
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setMonthlyIncome(userDto.getMonthlyIncome());
        user.setCreditScore(userDto.getCreditScore());
        User updatedUser = userRepository.save(user);
        UserDto updatedUserDto = new UserDto();
        BeanUtils.copyProperties(updatedUser, updatedUserDto);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null) throw new RuntimeException("User not found");
        userRepository.delete(user);
    }
}
