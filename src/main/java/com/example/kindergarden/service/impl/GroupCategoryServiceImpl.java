package com.example.kindergarden.service.impl;

import com.example.kindergarden.exception.ConflictException;
import com.example.kindergarden.exception.NotFoundException;
import com.example.kindergarden.mapper.GroupCategoryMapper;
import com.example.kindergarden.models.GroupCategory;
import com.example.kindergarden.models.dto.GroupCategoryCreateDto;
import com.example.kindergarden.models.dto.GroupCategoryDto;
import com.example.kindergarden.repositories.GroupCategoryRepository;
import com.example.kindergarden.repositories.GroupRepository;
import com.example.kindergarden.response.GlobalResponse;
import com.example.kindergarden.service.GroupCategoryService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GroupCategoryServiceImpl implements GroupCategoryService {
    private final GroupCategoryRepository groupCategoryRepository;
    private final GroupCategoryMapper groupCategoryMapper;
    private final GroupRepository groupRepository;

    public GroupCategoryServiceImpl(GroupCategoryRepository groupCategoryRepository, GroupRepository groupRepository) {
        this.groupCategoryRepository = groupCategoryRepository;
        this.groupRepository = groupRepository;
        this.groupCategoryMapper = GroupCategoryMapper.INSTANCE;
    }

    @Transactional
    @Override
    public ResponseEntity<GlobalResponse> createGroupCategory(GroupCategoryCreateDto groupCategoryCreateDto){
        if (groupCategoryRepository.existsByNameIgnoreCase(groupCategoryCreateDto.getCategoryName())){
            throw new ConflictException("Введённая категория уже существует!");
        }
        GroupCategory groupCategory = groupCategoryMapper.groupCategoryCreateDtoToGroupCategory(groupCategoryCreateDto);
        groupCategoryRepository.save(groupCategory);
        GroupCategoryDto groupCategoryDto = groupCategoryMapper.groupCategoryToGroupCategoryDto(groupCategory);

        GlobalResponse response = GlobalResponse.created(groupCategoryDto);\
        return ResponseEntity.status(201).body(response)
    }
    @Transactional
    @Override
    public ResponseEntity<GlobalResponse> updateGroupCategory(GroupCategoryDto groupCategoryDto, Long id) {
        GroupCategory groupCategory = groupCategoryRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Категория не найдена"));

        if (!groupCategory.getName().equals(groupCategoryDto.getName()) &&
        groupCategoryRepository.existsByNameIgnoreCase(groupCategoryDto.getName())){
            throw new ConflictException("Категорич с таким именем уже существует!");
        }
        groupCategoryMapper.updateGroupCategoryFromDto(groupCategoryDto, groupCategory);
        GroupCategory updatedGroupCategory = groupCategoryRepository.save(groupCategory);
        GroupCategoryDto groupCategoryDto1 = groupCategoryMapper.groupCategorysToGroupCategoryDtos(updatedGroupCategory);

        GlobalResponse response = GlobalResponse.created(groupCategoryDto);
        return ResponseEntity.status(201).body(response);
    }

    @Transactional
    @Override
    public ResponseEntity<GlobalResponse> deleteGroupCategory(Long id) {
        GroupCategory groupCategory = groupCategoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Категория не найдена"));
        if (groupRepository )
    }
}
