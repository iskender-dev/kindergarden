package com.example.kindergarden.mapper;

import com.example.kindergarden.models.Teacher;
import com.example.kindergarden.models.dto.TeacherCreateDto;
import com.example.kindergarden.models.dto.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    Teacher toEntity(TeacherCreateDto dto);
    TeacherDto toDto(Teacher entity);
}
