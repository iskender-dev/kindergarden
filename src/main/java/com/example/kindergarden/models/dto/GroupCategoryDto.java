package com.example.kindergarden.models.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupCategoryDto {
    private Long id;
    private String name;
    private boolean active;
    private Integer price;
}
