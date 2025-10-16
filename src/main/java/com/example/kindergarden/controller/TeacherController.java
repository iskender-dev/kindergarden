package com.example.kindergarden.controller;

import com.example.kindergarden.models.dto.TeacherCreateDto;
import com.example.kindergarden.models.dto.TeacherDto;
import com.example.kindergarden.service.TeacherService;
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
@RequestMapping("/teacher")
@Tag(name = "Teacher Controller", description = "Управление учителями")
@RequiredArgsConstructor
public class TeacherController implements CRUDController<TeacherCreateDto, TeacherDto> {

    private final TeacherService service;

    @Override
    @Operation(summary = "Создаем сущность учитель")
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherCreateDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Override
    @Operation(summary = "Обновление сущности учителя")
    public ResponseEntity<TeacherDto> update(@PathVariable Long id, @RequestBody TeacherCreateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Override
    @Operation(summary = "Поиск учителя по айди")
    public ResponseEntity<TeacherDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @Operation(summary = "Вывод всех учителей")
    public ResponseEntity<Page<TeacherDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @Override
    @Operation(summary = "Удаление сущности учителя")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
