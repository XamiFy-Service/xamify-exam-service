package com.xamify.exam.service;

import com.xamify.exam.dto.ExamRequest;
import com.xamify.exam.dto.ExamResponse;

import java.util.List;

public interface ExamService {
    ExamResponse createExam(ExamRequest examRequest);
    List<ExamResponse> getAllExams();
    List<ExamResponse> getUpcomingExams();
    ExamResponse getExamById(Long id);
    ExamResponse updateExam(Long id, ExamRequest examRequest);
    void deleteExam(Long id);
}