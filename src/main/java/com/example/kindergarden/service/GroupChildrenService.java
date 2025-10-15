package com.example.kindergarden.service;

import com.example.kindergarden.models.dto.EnrollChildDto;
import com.example.kindergarden.models.dto.WithdrawChildDto;
import com.example.kindergarden.response.GlobalResponse;
import org.springframework.http.ResponseEntity;

public interface GroupChildrenService {
    ResponseEntity<GlobalResponse> enrollChild(EnrollChildDto enrollChildDto);

    ResponseEntity<GlobalResponse> withdrawChild(Long id, WithdrawChildDto withdrawChildDto);
}
