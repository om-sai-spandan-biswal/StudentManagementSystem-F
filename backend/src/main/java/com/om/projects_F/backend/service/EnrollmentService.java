package com.om.projects_F.backend.service;

import com.om.projects_F.backend.dto.CourseDTO;
import com.om.projects_F.backend.dto.EnrollmentRequestDTO;
import com.om.projects_F.backend.dto.EnrollmentResponseDTO;
import com.om.projects_F.backend.dto.StudentDTO;
import com.om.projects_F.backend.entity.Course;
import com.om.projects_F.backend.entity.Enrollment;
import com.om.projects_F.backend.entity.Student;
import com.om.projects_F.backend.exception.ResourceNotFoundException;
import com.om.projects_F.backend.repository.CourseRepository;
import com.om.projects_F.backend.repository.EnrollmentRepository;
import com.om.projects_F.backend.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final StudentRepository studentRepository ;
    private final CourseRepository courseRepository ;
    private final EnrollmentRepository enrollmentRepository ;

    @Transactional
    public EnrollmentResponseDTO enrollStudentToCourse(EnrollmentRequestDTO request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("There is No Student with Id : " + request.getStudentId())) ;
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("There is No Course with Id : " + request.getCourseId())) ;

        Enrollment enrollment = new Enrollment() ;
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment) ;

        return new EnrollmentResponseDTO(
                savedEnrollment.getId(),
                student.getName(),
                course.getName(),
                savedEnrollment.getCreatedAt()
        );
    }

}
