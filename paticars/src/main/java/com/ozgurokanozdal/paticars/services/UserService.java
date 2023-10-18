package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.UserRepository;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserUpdateRequest;
import com.ozgurokanozdal.paticars.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

     private final UserRepository userRepository;
     private final ModelMapper modelMapper;
     private final PasswordEncoder passwordEncoder;

     public UserService(UserRepository userRepository,ModelMapper modelMapper,PasswordEncoder passwordEncoder){
         this.userRepository = userRepository;
         this.modelMapper = modelMapper;
         this.passwordEncoder = passwordEncoder;
     }


    public List<UserResponse> getAllUsers() {
         List<User> users =  userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserResponse.class)).collect(Collectors.toList());
    }

    public UserResponse saveNewUser(UserCreateRequest userCreate) {
         User user = modelMapper.map(userCreate,User.class);

         userRepository.save(user);
         return modelMapper.map(user,UserResponse.class);
    }

    public UserResponse getUserByIdWithResponse(Long userId) {
         // bu method user controllerda kullanılacak
         Optional<User> user = userRepository.findById(userId);
         if(user.isPresent()){
             return modelMapper.map(user,UserResponse.class);
         }else{

             return null;
         }

    }

    public User getUserById(Long userId) {
         return userRepository.findById(userId).orElse(null);
    }

    public ResponseEntity<UserResponse> updateUserById(Long userId, UserUpdateRequest userUpdate) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User oldUser = user.get();
            if(passwordEncoder.matches(userUpdate.getCurrentPassword(),oldUser.getPassword())){
                oldUser.setPassword(passwordEncoder.encode(userUpdate.getNewPassword()));
                userRepository.save(oldUser);
                return ResponseEntity.ok(modelMapper.map(oldUser,UserResponse.class));
            }else{
                UserResponse response = modelMapper.map(oldUser,UserResponse.class);
                return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
            }
        }else{
            //add custom exception
            return null;
        }
    }

    public void deleteById(Long userId) {
         userRepository.deleteById(userId);
    }
}
