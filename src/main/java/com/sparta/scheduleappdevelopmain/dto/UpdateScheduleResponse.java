package com.sparta.scheduleappdevelopmain.dto;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class UpdateScheduleResponse {

    private final String title;
    private final String content;
    private final Long UserId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(String title, String content, Long userId, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.title = title;
        this.content = content;
        this.UserId = userId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
