package com.om.projects_F.backend.service;

import com.om.projects_F.backend.dto.CourseDTO;
import com.om.projects_F.backend.dto.EnrollmentResponseDTO;
import com.om.projects_F.backend.entity.Course;
import com.om.projects_F.backend.entity.Enrollment;
import com.om.projects_F.backend.entity.Student;
import com.om.projects_F.backend.exception.ResourceNotFoundException;
import com.om.projects_F.backend.mapper.CourseMapper;
import com.om.projects_F.backend.repository.CourseRepository;
import com.om.projects_F.backend.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository ;
    private final CourseMapper courseMapper ;
    private final EnrollmentRepository enrollmentRepository ;


    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is No Student with Id : " + id)) ;
        return courseMapper.toDTO(course) ;
    }

    public List<CourseDTO> getAllCourse() {
        List<Course> courses = courseRepository.findAll() ;
        return courses
                .stream()
                .map(courseMapper::toDTO)
                .toList() ;
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO) ;
        Course createdCourse = courseRepository.save(course) ;
        return courseMapper.toDTO(createdCourse) ;
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO) ;
        course.setId(id);
        Course updatedCourse = courseRepository.save(course) ;
        return courseMapper.toDTO(updatedCourse) ;
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is No Student with Id : " + id)) ;
        course.setDeleted(true);
        courseRepository.save(course) ;
    }

    public List<CourseDTO> getAllCourseOfStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudent_Id(studentId) ;

        return enrollments.stream()
                .map(enrollment -> {
                    Course course = enrollment.getCourse() ;
                    return courseMapper.toDTO(course) ;
                }).toList() ;
    }
}
