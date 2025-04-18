package com.xamify.exam.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamRequest {
    private String title;
    private String description;
    private Integer durationMinutes;
    private LocalDateTime startTime;
    private LocalDateTime finalTime;
}
