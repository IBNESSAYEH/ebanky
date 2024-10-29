package com.YouCode.ebanky.services.implementation;

import com.YouCode.ebanky.entities.UserEntity;
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
        UserEntity checkUserByEmail = userRepository.findByEmail(userDto.getEmail());
        if (checkUserByEmail != null) throw new RuntimeException("User already exists");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword("user Password");
        userEntity.setUserId(util.generateStringId(32));
        UserEntity createdUser = userRepository.save(userEntity);
        UserDto createdUserDto = new UserDto();
        BeanUtils.copyProperties(createdUser, createdUserDto);

        return createdUserDto;
    }
}
