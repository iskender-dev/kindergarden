package com.example.kindergarden.service.impl;

import com.example.kindergarden.exception.ConflictException;
import com.example.kindergarden.exception.LogicException;
import com.example.kindergarden.exception.NotFoundException;
import com.example.kindergarden.mapper.GroupCategoryMapper;
import com.example.kindergarden.models.GroupCategory;
import com.example.kindergarden.models.dto.GroupCategoryCreateDto;
import com.example.kindergarden.models.dto.GroupCategoryDto;
import com.example.kindergarden.models.dto.PagedResponse;
import com.example.kindergarden.repositories.GroupCategoryRepository;
import com.example.kindergarden.repositories.GroupRepository;
import com.example.kindergarden.response.GlobalResponse;
import com.example.kindergarden.service.GroupCategoryService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(groupRepository.existsByGroupCategory(groupCategory))
            throw new ConflictException("Категория используется в группе");

        groupCategoryRepository.delete(groupCategory);

        GlobalResponse response = GlobalResponse.success("Категория групп с id - " + id + " удалена!");
        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<GlobalResponse> findGroupCategoryById(Long id) {
        GroupCategory groupCategory = groupCategoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Категория не найдена"));
        GroupCategoryDto groupCategoryDto = groupCategoryMapper.groupCategoryToGroupCategoryDto(groupCategory);

        GlobalResponse response = GlobalResponse.success(groupCategoryDto);
        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<GlobalResponse> findAllCategoryGroup(int pageNo, int pageSize) {
        if (pageNo < 0 || pageSize <= 0){
            throw new LogicException("Ошибка параметров пагинации");
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
        Page<GroupCategory> groupCategoryPage = groupCategoryRepository.findAll(pageable);
        List<GroupCategoryDto> groupCategoryDtos = groupCategoryMapper.groupCategorysToGroupCategoryDtos(
                groupCategoryPage.getContent());
        PagedResponse<GroupCategoryDto> pagedResponse = new PagedResponse<>(
                groupCategoryDtos,
                groupCategoryPage.getTotalElements(),
                groupCategoryPage.getTotalPages()
        );

        GlobalResponse response = GlobalResponse.success(pagedResponse);
        return ResponseEntity.status(200).body(response);
    }
}
