package com.om.projects_F.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(nullable = false,name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(nullable = false,name = "course_id")
    private Course course ;

    @CreationTimestamp
    private LocalDateTime createdAt ;
}
