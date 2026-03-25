package com.om.projects_F.backend.controller;

import com.om.projects_F.backend.dto.EnrollmentRequestDTO;
import com.om.projects_F.backend.dto.EnrollmentResponseDTO;
import com.om.projects_F.backend.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService ;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enrollStudent(@RequestBody EnrollmentRequestDTO request) {
        EnrollmentResponseDTO response = enrollmentService.enrollStudentToCourse(request) ;
        return new ResponseEntity<>(response, HttpStatus.CREATED) ;
    }

}
