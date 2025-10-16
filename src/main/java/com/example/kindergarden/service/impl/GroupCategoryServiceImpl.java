package com.example.kindergarden.service.impl;

import com.example.kindergarden.exception.ConflictException;
import com.example.kindergarden.exception.NotFoundException;
import com.example.kindergarden.mapper.GroupCategoryMapper;
import com.example.kindergarden.models.GroupCategory;
import com.example.kindergarden.models.dto.GroupCategoryCreateDto;
import com.example.kindergarden.models.dto.GroupCategoryDto;
import com.example.kindergarden.repositories.GroupCategoryRepository;
import com.example.kindergarden.repositories.GroupRepository;
import com.example.kindergarden.service.GroupCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCategoryServiceImpl implements GroupCategoryService {

    private final GroupCategoryRepository repository;
    private final GroupCategoryMapper mapper;
    private final GroupRepository groupRepo;

    @Override
    public GroupCategoryDto create(GroupCategoryCreateDto dto) {
        if (repository.existsByName(dto.getCategoryName())) {
            throw new ConflictException("Категория с таким названием уже существует");
        }

        GroupCategory saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public GroupCategoryDto update(Long id, GroupCategoryCreateDto dto) {
        GroupCategory existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Категория не найдена"));

        if (!existing.getName().equals(dto.getCategoryName()) && repository.existsByName(dto.getCategoryName())) {
            throw new ConflictException("Категория с таким названием уже существует");
        }

        existing.setName(dto.getCategoryName());
        existing.setActive(dto.getIsActive());
        existing.setPrice(dto.getPrice());
        return mapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Категория не найдена");
        }

        if (groupRepo.existsByGroupCategory_Id(id)) {
            throw new ConflictException("Нельзя удалить категорию, которая используется в группе");
        }

        repository.deleteById(id);
    }

    @Override
    public GroupCategoryDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Категория не найдена"));
    }

    @Override
    public Page<GroupCategoryDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }
}
