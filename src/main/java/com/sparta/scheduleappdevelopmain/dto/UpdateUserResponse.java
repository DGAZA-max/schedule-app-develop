package com.sparta.scheduleappdevelopmain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateUserResponse {

    private final String email;
    private final String password;
    private final String nickname;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateUserResponse(String email, String password, String nickname, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
