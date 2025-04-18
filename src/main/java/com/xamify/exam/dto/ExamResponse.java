package com.xamify.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamResponse {
    private Long id;
    private String title;
    private String description;
    private Integer durationMinutes;
    private LocalDateTime startTime;
    private LocalDateTime finalTime;
    private String status;
    private String message;
}
