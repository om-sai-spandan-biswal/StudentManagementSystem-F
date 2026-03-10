package com.om.projects_F.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id ;

    @NotBlank(message = "please provide name of course")
    private String name ;

    @NotBlank(message = "please provide code of course")
    private String courseCode ;

    @NotNull(message = "each course must have some credits")
    private Integer credit ;

    private String department ;
}
