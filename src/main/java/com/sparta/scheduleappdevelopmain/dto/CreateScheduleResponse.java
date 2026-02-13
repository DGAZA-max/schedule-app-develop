package com.sparta.scheduleappdevelopmain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {

    public final String title;
    public final String content;
    public final Long userId;
    public final LocalDateTime CreatedAt;

    public CreateScheduleResponse(String title, String content, Long userId, LocalDateTime createdAt) {

        this.title = title;
        this.content = content;
        this.userId = userId;
        this.CreatedAt = createdAt;
    }
}
