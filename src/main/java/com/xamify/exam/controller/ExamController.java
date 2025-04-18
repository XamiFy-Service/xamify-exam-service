package com.xamify.exam.controller;

import com.xamify.exam.dto.ExamRequest;
import com.xamify.exam.dto.ExamResponse;
import com.xamify.exam.restconnector.AuthClient;
import com.xamify.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private ExamResponse examResponse;


    @PostMapping("/create")
    public ResponseEntity<ExamResponse> createExam(@RequestBody ExamRequest examRequest,  @RequestHeader("Authorization") String token) {
        String role = authClient.validateToken(token);

        if ("ADMIN".equals(role)) {
            return ResponseEntity.ok(examService.createExam(examRequest));
        } else {
            examResponse.setStatus("Exam Creation Failed");
            examResponse.setMessage("You're Unauthorized to Create Exam");
            return ResponseEntity.status(403).body(examResponse);
        }

    }

    @GetMapping("/get")
    public ResponseEntity<List<ExamResponse>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<ExamResponse>> getUpcomingExams() {
        return ResponseEntity.ok(examService.getUpcomingExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponse> getExamById(@PathVariable Long id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ExamResponse> updateExam(@PathVariable Long id, @RequestBody ExamRequest examRequest, @RequestHeader("Authorization") String token) {
        String role = authClient.validateToken(token);

        if ("ADMIN".equals(role)) {
            return ResponseEntity.ok(examService.updateExam(id, examRequest));
        } else {
            return ResponseEntity.status(403).body(null);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        String role = authClient.validateToken(token);

        if ("ADMIN".equals(role)) {
            examService.deleteExam(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).body(null);
        }

    }

}
