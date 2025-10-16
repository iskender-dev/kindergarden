package com.example.kindergarden.controller;

import com.example.kindergarden.models.dto.GroupCategoryCreateDto;
import com.example.kindergarden.models.dto.GroupCategoryDto;
import com.example.kindergarden.service.GroupCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group-category")
@Tag(name = "GroupCategory controller", description = "Управление категориями групп")
@RequiredArgsConstructor
public class GroupCategoryController implements CRUDController<GroupCategoryCreateDto, GroupCategoryDto> {

    private final GroupCategoryService service;
    private final GroupCategoryService groupCategoryService;

    @Override
    @Operation(summary = "Создание категории групп")
    public ResponseEntity<GroupCategoryDto> create(@RequestBody @Valid GroupCategoryCreateDto dto) {
        GroupCategoryDto created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    @Override
    @Operation(summary = "Обновление категории групп")
    public ResponseEntity<GroupCategoryDto> update(@PathVariable Long id, @RequestBody GroupCategoryCreateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Override
    @Operation(summary = "Поиск категории групп по айди")
    public ResponseEntity<GroupCategoryDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @Operation(summary = "Вывод всех категорий групп")
    public ResponseEntity<Page<GroupCategoryDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @Override
    @Operation(summary = "Удаление категории групп")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

