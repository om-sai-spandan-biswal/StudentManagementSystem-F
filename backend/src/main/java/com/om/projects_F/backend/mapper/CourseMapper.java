package com.om.projects_F.backend.mapper;

import com.om.projects_F.backend.dto.CourseDTO;
import com.om.projects_F.backend.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDTO courseDTO) ;
    CourseDTO toDTO(Course course) ;
}
