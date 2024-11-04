package com.YouCode.ebanky.services;

import com.YouCode.ebanky.shared.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto findUserByUserId(String userId);
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUser(String userId);
}
