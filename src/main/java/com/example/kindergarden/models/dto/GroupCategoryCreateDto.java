package com.example.kindergarden.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupCategoryCreateDto {

    @NotBlank(message = "Поле 'Название категории' не может быть пустым")
    private String categoryName;

    @NotNull(message = "Необходимо указать, активна ли категория")
    private Boolean active;

    @NotNull(message = "Цена категории обязательна")
    @Positive(message = "Цена должна быть положительной")
    private Integer price;
}

