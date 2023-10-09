package com.ozgurokanozdal.paticars.requests;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserCreateRequest {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;

}
