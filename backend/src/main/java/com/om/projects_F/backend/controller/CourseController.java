package com.om.projects_F.backend.controller;

import com.om.projects_F.backend.dto.CourseDTO;
import com.om.projects_F.backend.dto.StudentDTO;
import com.om.projects_F.backend.service.CourseService;
import com.om.projects_F.backend.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService ;
    private final StudentService studentService ;

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable(name = "courseId") Long id) {
        CourseDTO courseDTO = courseService.getCourseById(id) ;
        return ResponseEntity.ok(courseDTO) ;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourse() {
        List<CourseDTO> courseDTOList = courseService.getAllCourse() ;
        return ResponseEntity.ok(courseDTOList) ;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO) ;
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED) ;
    }

    @PutMapping(path = "/{courseId}")
    public ResponseEntity<CourseDTO> updateCourseById( @PathVariable(name = "courseId") Long id,@RequestBody @Valid CourseDTO courseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO) ;
        return ResponseEntity.ok(updatedCourse) ;
    }

    @DeleteMapping(path = "/{courseId}")
    public void deleteCourseById(@PathVariable(name = "courseId") Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping(path = "/{courseId}/student")
    public ResponseEntity<List<StudentDTO>> getAllStudentByCourse(@PathVariable(name = "courseId") Long id) {
        List<StudentDTO> studentDTOList = studentService.getAllStudentOfCourse(id) ;
        return ResponseEntity.ok(studentDTOList) ;
    }

}
