package com.sparta.scheduleappdevelopmain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RegisterResponse {

    private final String nickname;
    private final LocalDateTime createdAt;

    public RegisterResponse(Long userId, String nickname, LocalDateTime createdAt){
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
}
