package com.ozgurokanozdal.paticars.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJwtResponse {

    private String message;
    private Long userId;
    private String username;
    private String accessToken;
    private String refreshToken;

}
