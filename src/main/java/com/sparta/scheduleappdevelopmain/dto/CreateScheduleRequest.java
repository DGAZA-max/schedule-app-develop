package com.sparta.scheduleappdevelopmain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleRequest {
    private Long userId;
    private String title;
    private String content;
}
