package com.om.projects_F.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String name ;

    @Column(nullable = false)
    private String email ;

    @Column(nullable = false)
    private Long contactNumber ;

    @Column(nullable = false)
    private LocalDateTime dateOfBirth ;

    private String department ;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments ;

    @CreationTimestamp
    private LocalDateTime createdAt ;
    @UpdateTimestamp
    private LocalDateTime updatedAt ;

    private Boolean deleted ; //for soft delete

}
