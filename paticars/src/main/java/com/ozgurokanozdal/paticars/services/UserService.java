package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.UserRepository;
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

    public User saveNewUser(User newUser) {
         return userRepository.save(newUser);
    }

    public User getUserById(Long userId) {
         return userRepository.findById(userId).orElse(null);
    }

    public User updateUserById(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User oldUser = user.get();
            oldUser.setName(newUser.getName());
            oldUser.setPassword(newUser.getPassword());
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
