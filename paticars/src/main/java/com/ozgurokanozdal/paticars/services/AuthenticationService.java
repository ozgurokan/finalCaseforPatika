package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.Enums.Role;
import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.UserRepository;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserJwtRequest;
import com.ozgurokanozdal.paticars.responses.UserJwtResponse;
import com.ozgurokanozdal.paticars.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public UserJwtResponse save(UserCreateRequest userCreateRequest) {
            User user = User.builder().username(userCreateRequest.getUsername())
                            .name(userCreateRequest.getName()).surname(userCreateRequest.getSurname())
                            .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                            .email(userCreateRequest.getEmail()).role(Role.USER).build();

            userRepository.save(user);

        var  token = jwtService.generateToken(user);


        return UserJwtResponse.builder().token(token).build();
    }


    public UserJwtResponse login(UserJwtRequest userJwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userJwtRequest.getUsername(),userJwtRequest.getPassword()));
        User user = userRepository.findByUsername(userJwtRequest.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);
        return UserJwtResponse.builder().token(token).build();


    }
}
