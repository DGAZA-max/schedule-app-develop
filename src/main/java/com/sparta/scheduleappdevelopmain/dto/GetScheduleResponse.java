package com.sparta.scheduleappdevelopmain.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {
    public final String title;
    public final String content;
    public final LocalDateTime CreatedAt;
    public final LocalDateTime ModifiedAt;

    public GetScheduleResponse(String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.title = title;
        this.content = content;
        this.CreatedAt = createdAt;
        this.ModifiedAt = modifiedAt;
    }
}
