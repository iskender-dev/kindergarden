package com.example.kindergarden.mapper;
import com.example.kindergarden.models.GroupCategory;
import com.example.kindergarden.models.dto.GroupCategoryCreateDto;
import com.example.kindergarden.models.dto.GroupCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface GroupCategoryMapper {

    @Mapping(source = "categoryName", target = "name")
    GroupCategory toEntity(GroupCategoryCreateDto dto);
    GroupCategoryDto toDto(GroupCategory entity);
}
