package com.example.kindergarden.controller;

import com.example.kindergarden.models.dto.GroupCreateDto;
import com.example.kindergarden.models.dto.GroupDto;
import com.example.kindergarden.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
@Tag(name = "Group Controller", description = "Управление группами")
@RequiredArgsConstructor
public class GroupController implements CRUDController<GroupCreateDto, GroupDto> {

    private final GroupService service;

    @Override
    @Operation(summary = "Создание группы")
    public ResponseEntity<GroupDto> create(@RequestBody GroupCreateDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Override
    @Operation(summary = "Обновление группы")
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @RequestBody GroupCreateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Override
    @Operation(summary = "Поиск группы по айди")
    public ResponseEntity<GroupDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @Operation(summary = "Вывод всех групп")
    public ResponseEntity<Page<GroupDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @Override
    @Operation(summary = "Удаление группы")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}


