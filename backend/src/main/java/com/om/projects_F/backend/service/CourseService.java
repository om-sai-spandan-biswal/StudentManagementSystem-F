package com.om.projects_F.backend.service;

import com.om.projects_F.backend.dto.CourseDTO;
import com.om.projects_F.backend.entity.Course;
import com.om.projects_F.backend.mapper.CourseMapper;
import com.om.projects_F.backend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository ;
    private final CourseMapper courseMapper ;

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow() ;
        return courseMapper.toDTO(course) ;
    }

    public List<CourseDTO> getAllCourse() {
        List<Course> courses = courseRepository.findAll() ;
        return courses
                .stream()
                .map(course -> courseMapper.toDTO(course))
                .toList() ;
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO) ;
        Course createdCourse = courseRepository.save(course) ;
        return courseMapper.toDTO(createdCourse) ;
    }

    public CourseDTO updateCourse(CourseDTO courseDTO, Long id) {
        Course course = courseMapper.toEntity(courseDTO) ;
        course.setId(id);
        Course updatedCourse = courseRepository.save(course) ;
        return courseMapper.toDTO(updatedCourse) ;
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow() ;
        course.setDeleted(true);
        courseRepository.save(course) ;
    }
}
