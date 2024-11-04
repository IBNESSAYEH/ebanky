package com.YouCode.ebanky.controllers;

import com.YouCode.ebanky.exceptios.UserException;
import com.YouCode.ebanky.requests.UserRequest;
import com.YouCode.ebanky.responses.ErrorMessages;
import com.YouCode.ebanky.responses.UserResponse;
import com.YouCode.ebanky.services.UserService;
import com.YouCode.ebanky.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse>  createUser(@RequestBody UserRequest userRequest) throws UserException {
        if(userRequest.getFirstName() == null || userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto createUser = userService.createUser(userDto);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createUser, userResponse);
        return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
   }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponse>   getUser(@PathVariable String userId) {

        UserDto userDto = userService.findUserByUserId(userId);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(userDto, userResponse);
       return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
   }
   @PutMapping(value = "/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
           produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
   public ResponseEntity<UserResponse>   updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) {

       UserDto userDto = new UserDto();
       BeanUtils.copyProperties(userRequest, userDto);
       UserDto updateUser = userService.updateUser(userId, userDto);
       UserResponse userResponse = new UserResponse();
       BeanUtils.copyProperties(updateUser, userResponse);
       return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
   }
   @DeleteMapping("/{userId}")
   public ResponseEntity<Object> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


   }

}
