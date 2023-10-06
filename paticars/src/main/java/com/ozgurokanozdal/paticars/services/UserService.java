package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.UserRepository;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserUpdateRequest;
import com.ozgurokanozdal.paticars.responses.UserResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

     private UserRepository userRepository;

     public UserService(UserRepository userRepository){
         this.userRepository = userRepository;
     }


    public List<UserResponse> getAllUsers() {
         List<User> users =  userRepository.findAll();
         List<UserResponse> userResponses = new ArrayList<>();

         //saçmalamış olabilirim kontrol et ama şu an uykum var
         for(User u : users){
             userResponses.add(new UserResponse(u));
         }
         return userResponses;
    }

    public UserResponse saveNewUser(UserCreateRequest userCreate) {
         User user = new User();
         user.setName(userCreate.getName());
         user.setSurname(userCreate.getSurname());
         user.setUsername(userCreate.getUsername());
         user.setPassword(userCreate.getPassword());
         user.setEmail(userCreate.getEmail());
         userRepository.save(user);

         UserResponse userResponse = new UserResponse(user);
         return userResponse;
    }

    public UserResponse getUserByIdWithResponse(Long userId) {
         // bu method user controllerda kullanılacak
         Optional<User> user = userRepository.findById(userId);
         if(user.isPresent()){
             User entity = user.get();
             UserResponse userResponse = new UserResponse();

             userResponse.setId(entity.getId());
             userResponse.setName(entity.getName());
             userResponse.setSurname(entity.getSurname());
             userResponse.setUsername(entity.getUsername());

             return userResponse;
         }else{
             // şimdilik
             return null;
         }

    }

    public User getUserById(Long userId) {
         // bu method car service de kullanılacak
         return userRepository.findById(userId).orElse(null);
    }

    public UserResponse updateUserById(Long userId, UserUpdateRequest userUpdate) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User oldUser = user.get();
            oldUser.setPassword(userUpdate.getPassword());
            userRepository.save(oldUser);
            UserResponse userResponse = new UserResponse(oldUser);
            return userResponse;
        }else{
            //add custom exception
            return null;
        }
    }

    public void deleteById(Long userId) {
         userRepository.deleteById(userId);
    }
}
