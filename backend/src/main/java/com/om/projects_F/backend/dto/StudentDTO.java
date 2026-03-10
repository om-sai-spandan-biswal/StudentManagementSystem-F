package com.om.projects_F.backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id ;

    @NotBlank(message = "You must provide a name")
    private String name ;

    @NotBlank(message = "You must provide a email")
    @Email(message = "Email should be Valid")
    private String email ;

    @NotNull(message = "You must provide contact number")
    @Pattern(regexp = "^\\d{10}$", message = "Please provide a Valid Mobile Number")
    private Long contactNumber ;

    @NotNull
    @Past
    private LocalDateTime dateOfBirth ;

    private String department ;
}
