package com.sparta.scheduleappdevelopmain.dto;


import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    private Long UserId;
    private String password;
    private String title;
    private String content;
}
