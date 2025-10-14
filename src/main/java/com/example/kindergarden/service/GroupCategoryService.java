package com.example.kindergarden.service;

import com.example.kindergarden.models.GroupCategory;
import com.example.kindergarden.models.dto.GroupCategoryCreateDto;
import org.springframework.http.ResponseEntity;

public interface GroupCategoryService {
    ResponseEntity<GlobalResponse> createGroupCategory(GroupCategoryCreateDto groupCategoryCreateDto);

}
