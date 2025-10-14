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
public class GroupDto {
    Long id;
    String name;
    Integer mexChildrenCount;
    Integer price;
    Long nannyId;
    Long groupCategoryId;
    Long teacherId;
}
