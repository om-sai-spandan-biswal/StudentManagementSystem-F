package com.om.projects_F.backend.repository;

import com.om.projects_F.backend.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findByStudent_Id(Long studentId) ;
    List<Enrollment> findByCourse_Id(Long courseId) ;
}
