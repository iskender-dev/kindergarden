package com.example.kindergarden.mapper;

import com.example.kindergarden.models.Child;
import com.example.kindergarden.models.GroupChildren;
import com.example.kindergarden.models.dto.EnrollChildDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChildMapper {
    ChildMapper INSTANCE = Mappers.getMapper(ChildMapper.class);

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "firstName", source = "child.firstName")
    @Mapping(target = "lastName", source = "child.lastName")
    @Mapping(target = "patronymic", source = "child.patronymic")
    @Mapping(target = "dateOfBirth", source = "child.dateOfBirth")
    EnrollChildDto toEnrollDto(GroupChildren groupChildren);

    Child toEntity(EnrollChildDto dto);
}
