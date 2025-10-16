package com.example.kindergarden.mapper;

import com.example.kindergarden.models.Child;
import com.example.kindergarden.models.dto.EnrollChildDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChildMapper {
    ChildMapper INSTANCE = Mappers.getMapper(ChildMapper.class);

    Child toEntity(EnrollChildDto dto);
    EnrollChildDto toDto(Child entity);
}
