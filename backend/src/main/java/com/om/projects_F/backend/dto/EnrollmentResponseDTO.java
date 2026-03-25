package com.om.projects_F.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponseDTO {

    private Long id ;
    private String studentName;
    private String courseName ;
    private LocalDateTime createdAt ;
}
