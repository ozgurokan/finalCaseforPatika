package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.UserRepository;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserUpdateRequest;
import com.ozgurokanozdal.paticars.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

     private UserRepository userRepository;
     private final ModelMapper modelMapper;

     public UserService(UserRepository userRepository,ModelMapper modelMapper){
         this.userRepository = userRepository;
         this.modelMapper = modelMapper;
     }


    public List<UserResponse> getAllUsers() {
         List<User> users =  userRepository.findAll();
         List<UserResponse> userResponses = users.stream().map(user -> modelMapper.map(user,UserResponse.class)).collect(Collectors.toList());
         return userResponses;
    }

    public UserResponse saveNewUser(UserCreateRequest userCreate) {
         User user = modelMapper.map(userCreate,User.class);
//         user.setName(userCreate.getName());
//         user.setSurname(userCreate.getSurname());
//         user.setUsername(userCreate.getUsername());
//         user.setPassword(userCreate.getPassword());
//         user.setEmail(userCreate.getEmail());
         userRepository.save(user);
         return modelMapper.map(user,UserResponse.class);
    }

    public UserResponse getUserByIdWithResponse(Long userId) {
         // bu method user controllerda kullanılacak
         Optional<User> user = userRepository.findById(userId);
         if(user.isPresent()){
//             User entity = user.get();
//             UserResponse userResponse = new UserResponse();
//
//             userResponse.setId(entity.getId());
//             userResponse.setName(entity.getName());
//             userResponse.setSurname(entity.getSurname());
//             userResponse.setUsername(entity.getUsername());

             return modelMapper.map(user,UserResponse.class);
         }else{
             // şimdilik
             return null;
         }

    }

    public User getUserById(Long userId) {
         // bu method car service de kullanılacak o yüzden DTO kullanmadım
         return userRepository.findById(userId).orElse(null);
    }

    public UserResponse updateUserById(Long userId, UserUpdateRequest userUpdate) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User oldUser = user.get();
            oldUser.setPassword(userUpdate.getPassword());
            userRepository.save(oldUser);
;
            return modelMapper.map(oldUser,UserResponse.class);
        }else{
            //add custom exception
            return null;
        }
    }

    public void deleteById(Long userId) {
         userRepository.deleteById(userId);
    }
}
