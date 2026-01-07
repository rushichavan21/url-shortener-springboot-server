package com.rushi.urlShortener.dto.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
}