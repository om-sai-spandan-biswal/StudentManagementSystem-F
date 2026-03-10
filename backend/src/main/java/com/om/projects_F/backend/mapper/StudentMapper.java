package com.om.projects_F.backend.mapper;

import com.om.projects_F.backend.dto.StudentDTO;
import com.om.projects_F.backend.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student) ;
    Student toEntity(StudentDTO studentDTO) ;
}
