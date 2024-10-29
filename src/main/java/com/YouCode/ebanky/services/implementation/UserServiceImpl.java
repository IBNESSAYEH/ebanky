package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.UserEntity;
import com.YouCode.ebanky.repositories.UserRepository;
import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword("user Password");
        userEntity.setUserId("user ID");
        UserEntity createdUser = userRepository.save(userEntity);
        UserDto createdUserDto = new UserDto();
        BeanUtils.copyProperties(createdUser, createdUserDto);

        return createdUserDto;
    }
}
