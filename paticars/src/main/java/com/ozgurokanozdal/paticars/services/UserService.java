package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.UserRepository;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

     private UserRepository userRepository;

     public UserService(UserRepository userRepository){
         this.userRepository = userRepository;
     }


    public List<User> getAllUsers() {
         return userRepository.findAll();
    }

    public User saveNewUser(UserCreateRequest userCreate) {
         User user = new User();
         user.setName(userCreate.getName());
         user.setSurname(userCreate.getSurname());
         user.setUsername(userCreate.getUsername());
         user.setPassword(userCreate.getPassword());
         user.setEmail(userCreate.getEmail());
         return userRepository.save(user);
    }

    public User getUserById(Long userId) {
         return userRepository.findById(userId).orElse(null);
    }

    public User updateUserById(Long userId, UserUpdateRequest userUpdate) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User oldUser = user.get();
            oldUser.setPassword(userUpdate.getPassword());
            userRepository.save(oldUser);
            return oldUser;
        }else{
            //add custom exception
            return null;
        }
    }

    public void deleteById(Long userId) {
         userRepository.deleteById(userId);
    }
}
