package com.example.kindergarden.controller;

import com.example.kindergarden.models.dto.EnrollChildDto;
import com.example.kindergarden.models.dto.WithdrawChildDto;
import com.example.kindergarden.response.GlobalResponse;
import com.example.kindergarden.service.GroupChildrenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group-children")
@Tag(name = "Group Children Controller", description = "Управление детьми в группах")
@RequiredArgsConstructor
public class GroupChildrenController {

    private final GroupChildrenService service;

    @PostMapping
    @Operation(summary = "Зачисление ребенка в группу")
    public ResponseEntity<ResponseEntity<GlobalResponse>> enroll(@RequestBody EnrollChildDto dto) {
        return ResponseEntity.ok(service.enrollChild(dto));
    }

    @PutMapping("/{id}/withdraw")
    @Operation(summary = "Отчисление ребенка из группы")
    public ResponseEntity<ResponseEntity<GlobalResponse>> withdraw(@PathVariable Long id, @RequestBody WithdrawChildDto dto) {
        return ResponseEntity.ok(service.withdrawChild(id, dto));
    }
}
