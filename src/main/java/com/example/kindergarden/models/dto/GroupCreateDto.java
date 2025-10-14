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
public class GroupCreateDto {

    @NotBlank(message = "Название группы обязательно")
    private String groupName;

    @NotNull(message = "Максимальное количество детей обязательно")
    @Positive(message = "Количество детей должно быть положительным")
    private Integer maxCapacity;

    @NotNull(message = "Цена группы обязательна")
    @Positive(message = "Цена должна быть положительной")
    private Integer price;

    @NotNull(message = "Необходимо указать ID няни")
    private Long nannyId;

    @NotNull(message = "Необходимо указать ID категории группы")
    private Long categoryId;

    @NotNull(message = "Необходимо указать ID учителя")
    private Long teacherId;
}
