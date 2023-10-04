package com.ozgurokanozdal.paticars.controllers;

import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserUpdateRequest;
import com.ozgurokanozdal.paticars.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    };

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody UserCreateRequest userCreate){
        return userService.saveNewUser(userCreate);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdate){
        return userService.updateUserById(userId,userUpdate);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
