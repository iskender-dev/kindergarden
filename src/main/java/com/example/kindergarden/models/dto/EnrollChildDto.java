package com.example.kindergarden.models.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EnrollChildDto {
    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    private String patronymic;

    @NotNull(message = "Дата рождения обязательна")
    private LocalDate dateOfBirth;

    @NotNull(message = "ID группы обязателен")
    private Long groupId;

    private Integer price;

}

