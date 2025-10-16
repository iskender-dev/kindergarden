package com.example.kindergarden.mapper;

import com.example.kindergarden.models.Group;
import com.example.kindergarden.models.dto.GroupCreateDto;
import com.example.kindergarden.models.dto.GroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class, GroupCategoryMapper.class})
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    // Явно указываем маппинг groupName -> name
    @Mapping(source = "groupName", target = "name")
    @Mapping(source = "maxCapacity", target = "maxChildrenCount")
    Group toEntity(GroupCreateDto dto);

    GroupDto toDto(Group entity);
}
