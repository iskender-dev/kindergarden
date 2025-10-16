package com.example.kindergarden.models.dto;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCategoryDto {
    private Long id;
    private String categoryName;
    private Integer price;
    private Boolean active;
}