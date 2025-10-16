package com.example.kindergarden.service;

import com.example.kindergarden.models.dto.TeacherCreateDto;
import com.example.kindergarden.models.dto.TeacherDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    TeacherDto create(TeacherCreateDto dto);
    TeacherDto update(Long id, TeacherCreateDto dto);
    void delete(Long id);
    TeacherDto findById(Long id);
    Page<TeacherDto> findAll(Pageable pageable);
}
