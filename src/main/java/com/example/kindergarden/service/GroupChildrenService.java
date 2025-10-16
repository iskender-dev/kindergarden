package com.example.kindergarden.service;

import com.example.kindergarden.models.dto.EnrollChildDto;
import com.example.kindergarden.models.dto.WithdrawChildDto;

public interface GroupChildrenService {

    EnrollChildDto enrollChild(EnrollChildDto dto);

    EnrollChildDto withdrawChild(Long id, WithdrawChildDto dto);

}
