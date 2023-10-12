package com.ozgurokanozdal.paticars.controllers;

import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserUpdateRequest;
import com.ozgurokanozdal.paticars.responses.UserResponse;
import com.ozgurokanozdal.paticars.services.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userCreate){
        return ResponseEntity.ok(userService.saveNewUser(userCreate));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getOneUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserByIdWithResponse(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateOneUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdate){
        return ResponseEntity.ok(userService.updateUserById(userId,userUpdate));
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
