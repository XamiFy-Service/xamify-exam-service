package com.xamify.exam.service;

import com.xamify.exam.dto.ExamRequest;
import com.xamify.exam.dto.ExamResponse;
import com.xamify.exam.model.Exam;
import com.xamify.exam.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Override
    public ExamResponse createExam(ExamRequest examRequest) {
        Exam exam = new Exam();
        exam.setTitle(examRequest.getTitle());
        exam.setDescription(examRequest.getDescription());
        exam.setDurationMinutes(examRequest.getDurationMinutes());
        exam.setStartTime(examRequest.getStartTime());
        exam.setFinalTime(examRequest.getFinalTime());

        exam = examRepository.save(exam);

        return mapToResponse(exam);
    }

    @Override
    public List<ExamResponse> getAllExams() {
        return examRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamResponse> getUpcomingExams() {
        return examRepository.findByStartTimeAfter(LocalDateTime.now())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ExamResponse getExamById(Long id) {
        Exam exam = examRepository.findById(id).orElseThrow();
        return mapToResponse(exam);
    }

    @Override
    public ExamResponse updateExam(Long id, ExamRequest examRequest) {
        Exam exam = examRepository.findById(id).orElseThrow();
        exam.setTitle(examRequest.getTitle());
        exam.setDescription(examRequest.getDescription());
        exam.setDurationMinutes(examRequest.getDurationMinutes());
        exam.setStartTime(examRequest.getStartTime());
        exam.setFinalTime(examRequest.getFinalTime());

        exam = examRepository.save(exam);
        return mapToResponse(exam);
    }

    @Override
    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    private ExamResponse mapToResponse(Exam exam) {
        ExamResponse res = new ExamResponse();
        res.setId(exam.getId());
        res.setTitle(exam.getTitle());
        res.setDescription(exam.getDescription());
        res.setDurationMinutes(exam.getDurationMinutes());
        res.setStartTime(exam.getStartTime());
        res.setFinalTime(exam.getFinalTime());
        return res;
    }
}
