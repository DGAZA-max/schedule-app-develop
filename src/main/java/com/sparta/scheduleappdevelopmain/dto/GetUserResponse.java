package com.sparta.scheduleappdevelopmain.dto;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public class GetUserResponse {

    private final Long id;
    private final String email;
    private final String nickname;

    public GetUserResponse(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}
