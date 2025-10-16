package com.example.kindergarden.service.impl;

import com.example.kindergarden.exception.ConflictException;
import com.example.kindergarden.exception.NotFoundException;
import com.example.kindergarden.mapper.TeacherMapper;
import com.example.kindergarden.models.Teacher;
import com.example.kindergarden.models.dto.TeacherCreateDto;
import com.example.kindergarden.models.dto.TeacherDto;
import com.example.kindergarden.repositories.GroupRepository;
import com.example.kindergarden.repositories.TeacherRepository;
import com.example.kindergarden.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;
    private final GroupRepository groupRepo;
    private final TeacherMapper mapper;

    @Override
    public TeacherDto create(TeacherCreateDto dto) {
        if (repository.existsByFirstNameAndLastNameAndPatronymicAndTeacherDegree(
                dto.getFirstName(), dto.getLastName(), dto.getPatronymic(), dto.getTeacherDegree())) {
            throw new ConflictException("Такой учитель уже существует");
        }

        if (dto.getTeacherDegree() == null) {
            throw new ConflictException("Роль преподавателя обязательна");
        }

        Teacher teacher = mapper.toEntity(dto);
        return mapper.toDto(repository.save(teacher));
    }

    @Override
    public TeacherDto update(Long id, TeacherCreateDto dto) {
        Teacher teacher = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Учитель не найден"));

        boolean duplicate = repository.existsByFirstNameAndLastNameAndPatronymicAndTeacherDegree(
                dto.getFirstName(), dto.getLastName(), dto.getPatronymic(), dto.getTeacherDegree());
        if (duplicate && !teacher.getFirstName().equals(dto.getFirstName()) &&
                !teacher.getLastName().equals(dto.getLastName())) {
            throw new ConflictException("Учитель с такими данными уже существует");
        }

        if (dto.getTeacherDegree() == null) {
            throw new ConflictException("Роль преподавателя обязательна");
        }

        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setPatronymic(dto.getPatronymic());
        teacher.setDegree(dto.getTeacherDegree());
        teacher.setActive(dto.getActive());

        return mapper.toDto(repository.save(teacher));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Учитель не найден"));

        if (groupRepo.existsByTeacher_Id(id) || groupRepo.existsByNanny_Id(id)) {
            throw new ConflictException("Нельзя удалить учителя, который назначен в группе");
        }

        repository.deleteById(id);
    }

    @Override
    public TeacherDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Учитель не найден"));
    }

    @Override
    public Page<TeacherDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }
}
