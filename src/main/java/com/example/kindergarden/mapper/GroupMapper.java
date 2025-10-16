package com.example.kindergarden.mapper;

import com.example.kindergarden.models.Group;
import com.example.kindergarden.models.dto.GroupCreateDto;
import com.example.kindergarden.models.dto.GroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class, GroupCategoryMapper.class})
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    Group toEntity(GroupCreateDto dto);
    GroupDto toDto(Group entity);
}
