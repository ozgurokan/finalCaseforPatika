package com.ozgurokanozdal.paticars.services;

import com.ozgurokanozdal.paticars.Enums.Role;
import com.ozgurokanozdal.paticars.entities.RefreshToken;
import com.ozgurokanozdal.paticars.entities.User;
import com.ozgurokanozdal.paticars.repositories.UserRepository;
import com.ozgurokanozdal.paticars.requests.RefreshRequest;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserJwtRequest;
import com.ozgurokanozdal.paticars.responses.UserJwtResponse;
import com.ozgurokanozdal.paticars.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final RefreshTokenService refreshTokenService;



    public ResponseEntity<UserJwtResponse> save(UserCreateRequest userCreateRequest) {

        if(userRepository.findByUsername(userCreateRequest.getUsername()).isPresent()){
            UserJwtResponse response = UserJwtResponse.builder().message("Username already in use!").build();
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        } else if (userRepository.findByEmail(userCreateRequest.getEmail()).isPresent()) {
            UserJwtResponse response = UserJwtResponse.builder().message("Email already in use!").build();
            return  new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }
        User user = User.builder().username(userCreateRequest.getUsername())
                            .name(userCreateRequest.getName()).surname(userCreateRequest.getSurname())
                            .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                            .email(userCreateRequest.getEmail()).role(Role.USER).build();

            userRepository.save(user);

        String accessToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(user);

        UserJwtResponse response = UserJwtResponse.builder().message("Registration Successful").userId(user.getId()).accessToken(accessToken).refreshToken(refreshToken).build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    public UserJwtResponse login(UserJwtRequest userJwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userJwtRequest.getUsername(),userJwtRequest.getPassword()));
        User user = userRepository.findByUsername(userJwtRequest.getUsername()).orElseThrow();


        String token = jwtService.generateToken(user);
        return UserJwtResponse.builder().accessToken(token).refreshToken(refreshTokenService.createRefreshToken(user)).userId(user.getId()).build();
    }


    public ResponseEntity<UserJwtResponse> refresh(RefreshRequest refreshRequest) {
        RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
        if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
                !refreshTokenService.isRefreshTokenExpired(token)) {

            User user = token.getUser();
            String jwtToken = jwtService.generateToken(user);
            UserJwtResponse response = UserJwtResponse.builder().message("token successfully refreshed.").accessToken("Bearer " + jwtToken).userId(user.getId()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            UserJwtResponse response = UserJwtResponse.builder().message("token is not valid!").build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
