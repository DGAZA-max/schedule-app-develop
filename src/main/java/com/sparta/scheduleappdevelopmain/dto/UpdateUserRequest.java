package com.sparta.scheduleappdevelopmain.dto;


import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private String email;
    private String password;
    private String nickname;
}
