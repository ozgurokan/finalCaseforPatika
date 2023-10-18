package com.ozgurokanozdal.paticars.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String newPassword;
    private String currentPassword;
}

