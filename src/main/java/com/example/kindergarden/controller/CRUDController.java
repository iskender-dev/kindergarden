package com.example.kindergarden.controller;

import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CRUDController<CreateDto, Dto> {

    @PostMapping
    ResponseEntity<Dto> create(@RequestBody CreateDto dto);

    @PutMapping("/{id}")
    ResponseEntity<Dto> update(@Valid @PathVariable Long id, @RequestBody CreateDto dto);

    @GetMapping("/{id}")
    ResponseEntity<Dto> findById(@Valid @PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<Dto>> findAll(@Valid @ParameterObject Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@Valid @PathVariable Long id);
}

