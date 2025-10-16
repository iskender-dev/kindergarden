package com.example.kindergarden.controller;

import com.example.kindergarden.models.dto.PaymentCreateDto;
import com.example.kindergarden.models.dto.PaymentDto;
import com.example.kindergarden.models.dto.PreviousMonthDebtDto;
import com.example.kindergarden.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payments Controller", description = "Оплата за садик")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Создание оплаты")
    public ResponseEntity<PaymentDto> addPayment(@RequestBody PaymentCreateDto dto) {
        return ResponseEntity.ok(paymentService.create(dto));
    }

    @GetMapping("/previous-month/{childId}")
    @Operation(summary = "Получить задолженность за прошлый месяц")
    public ResponseEntity<PreviousMonthDebtDto> getDebt(@PathVariable Long childId) {
        return ResponseEntity.ok(paymentService.getPreviousMonthDebt(childId));
    }
}