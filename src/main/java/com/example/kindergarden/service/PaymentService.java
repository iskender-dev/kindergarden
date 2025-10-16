package com.example.kindergarden.service;

import com.example.kindergarden.models.dto.PaymentCreateDto;
import com.example.kindergarden.models.dto.PaymentDto;
import com.example.kindergarden.models.dto.PreviousMonthDebtDto;

public interface PaymentService {
    PaymentDto create(PaymentCreateDto dto);
    PreviousMonthDebtDto getPreviousMonthDebt(Long childId);
}
