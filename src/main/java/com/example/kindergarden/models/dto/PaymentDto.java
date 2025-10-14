package com.example.kindergarden.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    @NotNull(message = "ID должен быть заполнен")
    Long id;
    @NotNull(message = "Введите сумму")
    @Positive(message = "Сумма должна быть больше 0")
    Integer amount;
    @NotNull(message = "Введите дату платежа")
    LocalDate paymentDate;
}
