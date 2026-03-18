package com.om.projects_F.backend.service;

import com.om.projects_F.backend.dto.StudentDTO;
import com.om.projects_F.backend.entity.Student;
import com.om.projects_F.backend.mapper.StudentMapper;
import com.om.projects_F.backend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository ;
    private final StudentMapper studentMapper ;


    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow() ;
        return studentMapper.toDTO(student) ;
    }

    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll() ;
        return students
                .stream()
                .map(studentMapper::toDTO)
                .toList() ;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO) ;
        Student createdStudent = studentRepository.save(student) ;
        return studentMapper.toDTO(createdStudent) ;
    }

    public StudentDTO updateStudent(Long id,StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO) ;
        student.setId(id);
        student.setUpdatedAt(LocalDateTime.now());
        Student updatedStudent = studentRepository.save(student) ;
        return studentMapper.toDTO(student) ;
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow() ;
        student.setDeleted(true);
        studentRepository.save(student) ;
    }
}
