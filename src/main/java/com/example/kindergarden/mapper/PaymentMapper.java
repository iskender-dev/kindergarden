package com.example.kindergarden.mapper;

import com.example.kindergarden.models.Payment;
import com.example.kindergarden.models.dto.PaymentCreateDto;
import com.example.kindergarden.models.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toEntity(PaymentCreateDto dto);
    @Mapping(source = "groupChildren.id", target = "groupChildrenId")
    PaymentDto toDto(Payment entity);
}
