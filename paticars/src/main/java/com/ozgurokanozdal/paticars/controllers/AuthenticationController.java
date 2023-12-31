package com.ozgurokanozdal.paticars.controllers;

import com.ozgurokanozdal.paticars.requests.RefreshRequest;
import com.ozgurokanozdal.paticars.requests.UserCreateRequest;
import com.ozgurokanozdal.paticars.requests.UserJwtRequest;
import com.ozgurokanozdal.paticars.responses.UserJwtResponse;
import com.ozgurokanozdal.paticars.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/save")
    public ResponseEntity<UserJwtResponse> save(@RequestBody UserCreateRequest userCreateRequest){
        return authenticationService.save(userCreateRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<UserJwtResponse> login(@RequestBody UserJwtRequest userJwtRequest){
        return ResponseEntity.ok(authenticationService.login(userJwtRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserJwtResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        return  authenticationService.refresh(refreshRequest);

    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }
}
