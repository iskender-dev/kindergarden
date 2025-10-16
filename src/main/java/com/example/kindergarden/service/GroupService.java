package com.example.kindergarden.service;

import com.example.kindergarden.models.dto.GroupCreateDto;
import com.example.kindergarden.models.dto.GroupDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    GroupDto create(GroupCreateDto dto);
    GroupDto update(Long id, GroupCreateDto dto);
    GroupDto findById(Long id);
    void delete(Long id);
    Page<GroupDto> findAll(Pageable pageable);
}
