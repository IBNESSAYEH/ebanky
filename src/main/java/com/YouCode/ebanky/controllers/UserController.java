package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.requests.UserRequest;
import com.YouCode.ebanky.responses.UserResponse;
import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
   public UserResponse createUser(@RequestBody UserRequest userRequest) {
       System.out.println("create user");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto createUser = userService.createUser(userDto);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createUser, userResponse);
        return userResponse;
   }
   @GetMapping
   public void getUser(){
       System.out.println("get user");
   }
   @PutMapping
   public void updateUser(){
       System.out.println("update user");
   }
   @DeleteMapping
   public void deleteUser(){
       System.out.println("delete user");
   }

}
