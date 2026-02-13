package com.sparta.scheduleappdevelopmain.dto;

import lombok.Getter;

@Getter
// SessionUser : 로그인에 성공한 유저의 최소한의 정보를 꺼내쓰기 위한 DTO
public class SessionUser {

    private final Long id;
    public final String email;

    public SessionUser(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
