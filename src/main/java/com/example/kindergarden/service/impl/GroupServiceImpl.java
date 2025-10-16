package com.example.kindergarden.service.impl;

import com.example.kindergarden.exception.ConflictException;
import com.example.kindergarden.exception.NotFoundException;
import com.example.kindergarden.mapper.GroupMapper;
import com.example.kindergarden.models.Group;
import com.example.kindergarden.models.GroupCategory;
import com.example.kindergarden.models.Teacher;
import com.example.kindergarden.models.dto.GroupCreateDto;
import com.example.kindergarden.models.dto.GroupDto;
import com.example.kindergarden.repositories.GroupCategoryRepository;
import com.example.kindergarden.repositories.GroupChildrenRepository;
import com.example.kindergarden.repositories.GroupRepository;
import com.example.kindergarden.repositories.TeacherRepository;
import com.example.kindergarden.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final GroupCategoryRepository categoryRepository;
    private final GroupChildrenRepository groupChildrenRepository;
    private final GroupMapper mapper;

    @Override
    public GroupDto create(GroupCreateDto dto) {
        if (groupRepository.existsByName((dto.getGroupName())) {
            throw new ConflictException("Группа с таким названием уже существует");
        }

        Teacher nanny = teacherRepository.findById(dto.getNannyId())
                .orElseThrow(() -> new NotFoundException("Няня не найдена"));
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new NotFoundException("Учитель не найден"));
        GroupCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Категория не найдена"));

        if (!category.getActive()) {
            throw new ConflictException("Категория групп неактивна, нельзя создать группу");
        }
        if (!teacher.getActive()) {
            throw new ConflictException("Учитель неактивен");
        }
        if (!nanny.getActive()) {
            throw new ConflictException("Няня неактивна");
        }

        Group group = mapper.toEntity(dto);
        group.setNanny(nanny);
        group.setTeacher(teacher);
        group.setGroupCategory(category);
        return mapper.toDto(groupRepository.save(group));
    }

    @Override
    public GroupDto update(Long id, GroupCreateDto dto) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Группа не найдена"));

        if (!group.getName().equals(dto.getGroupName()) && groupRepository.existsByName(dto.getName())) {
            throw new ConflictException("Группа с таким названием уже существует");
        }


        group.setName(dto.getGroupName());
        group.setPrice(dto.getPrice());
        group.setMaxChildrenCount(dto.getMaxCapacity());

        return mapper.toDto(groupRepository.save(group));
    }

    @Override
    public GroupDto findById(Long id) {
        return groupRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Группа не найдена"));
    }

    @Override
    public void delete(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Группа не найдена"));

        if (groupChildrenRepository.existsByGroup_IdAndEndDateIsNull((group.getId())) {
            throw new ConflictException("Нельзя удалить группу, в которой есть дети");
        }

        groupRepository.deleteById(id);
    }

    @Override
    public Page<GroupDto> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable).map(mapper::toDto);
    }
}


