package com.om.projects_F.backend.service;

import com.om.projects_F.backend.dto.StudentDTO;
import com.om.projects_F.backend.entity.Enrollment;
import com.om.projects_F.backend.entity.Student;
import com.om.projects_F.backend.exception.ResourceNotFoundException;
import com.om.projects_F.backend.mapper.StudentMapper;
import com.om.projects_F.backend.repository.EnrollmentRepository;
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
    private final EnrollmentRepository enrollmentRepository ;



    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is No Student with Id : " + id)) ;
        if(student.getDeleted()) throw new ResourceNotFoundException("Student is Already Deleted") ;
        return studentMapper.toDTO(student) ;
    }

    public List<StudentDTO> getAllStudent() {
        List<Student> students = studentRepository.findAll() ;
        return students
                .stream()
                .map(student -> {
                    if(student.getDeleted()) return null ;
                    return studentMapper.toDTO(student);
                })
                .toList() ;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO) ;
        Student createdStudent = studentRepository.save(student) ;
        return studentMapper.toDTO(createdStudent) ;
    }

    public List<StudentDTO> createMultipleStudent(List<StudentDTO> studentDTOS) {
        List<Student> students = studentDTOS.stream()
                .map(studentMapper::toEntity)
                .toList() ;
        List<Student> savedStudents = studentRepository.saveAll(students) ;
        return savedStudents.stream()
                .map(studentMapper::toDTO)
                .toList() ;
    }

    public StudentDTO updateStudent(Long id,StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO) ;
        if(student.getDeleted()) throw new ResourceNotFoundException("There is No Student with Id : " + id) ;
        student.setId(id);
        student.setUpdatedAt(LocalDateTime.now());
        Student updatedStudent = studentRepository.save(student) ;
        return studentMapper.toDTO(updatedStudent) ;
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is No Student with Id : " + id)) ;
        student.setDeleted(true);
        studentRepository.save(student) ;
    }

    public List<StudentDTO> getAllStudentOfCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourse_Id(courseId) ;

        return enrollments.stream()
                .map(enrollment -> {
                    Student student = enrollment.getStudent() ;
                    return studentMapper.toDTO(student) ;
                }).toList() ;
    }
}
