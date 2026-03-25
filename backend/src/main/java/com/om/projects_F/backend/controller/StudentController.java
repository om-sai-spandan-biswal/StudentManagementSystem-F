package com.om.projects_F.backend.controller;

import com.om.projects_F.backend.dto.CourseDTO;
import com.om.projects_F.backend.dto.DeleteResponseDTO;
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
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService ;
    private final CourseService courseService ;

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "studentId") Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id) ;
        return ResponseEntity.ok(studentDTO) ;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent(
            @RequestParam(defaultValue = "") String name ,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
            ) {
        List<StudentDTO> studentDTOList = studentService.getAllStudent(name,pageNumber,sortBy,sortDirection) ;
        return ResponseEntity.ok(studentDTOList) ;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO) ;
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED) ;
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<StudentDTO>> createBulkStudent(@RequestBody @Valid List<StudentDTO> studentDTOS) {
        List<StudentDTO> studentDTOList = studentService.createMultipleStudent(studentDTOS) ;
        return new ResponseEntity<>(studentDTOList,HttpStatus.CREATED) ;
    }

    @PutMapping(path = "/{studentId}")
    public ResponseEntity<StudentDTO> updateStudentById(@PathVariable(name = "studentId") Long id,@RequestBody @Valid StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO) ;
        return ResponseEntity.ok(updatedStudent) ;
    }

    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<DeleteResponseDTO> deleteById(@PathVariable(name = "studentId") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new DeleteResponseDTO("Successfully Student deleted with Id : " + id)) ;
    }

    @GetMapping(path = "/{studentId}/course")
    public ResponseEntity<List<CourseDTO>> getAllCourseByStudent(@PathVariable(name = "studentId") Long id) {
        List<CourseDTO> courseDTOList = courseService.getAllCourseOfStudent(id) ;
        return ResponseEntity.ok(courseDTOList) ;
    }
}
