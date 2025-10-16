package com.example.kindergarden.service.impl;

import com.example.kindergarden.exception.ConflictException;
import com.example.kindergarden.exception.NotFoundException;
import com.example.kindergarden.mapper.ChildMapper;
import com.example.kindergarden.models.Child;
import com.example.kindergarden.models.Group;
import com.example.kindergarden.models.GroupChildren;
import com.example.kindergarden.models.dto.EnrollChildDto;
import com.example.kindergarden.models.dto.WithdrawChildDto;
import com.example.kindergarden.repositories.ChildRepository;
import com.example.kindergarden.repositories.GroupChildrenRepository;
import com.example.kindergarden.repositories.GroupRepository;
import com.example.kindergarden.response.GlobalResponse;
import com.example.kindergarden.service.GroupChildrenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GroupChildrenServiceImpl implements GroupChildrenService {

    private final GroupChildrenRepository groupChildrenRepository;
    private final ChildRepository childRepository;
    private final GroupRepository groupRepository;
    private final ChildMapper mapper;

    @Override
    public ResponseEntity<GlobalResponse> enrollChild(EnrollChildDto dto) {
        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new NotFoundException("Группа не найдена"));

        if (!group.getGroupCategory().getActive()) {
            throw new ConflictException("Категория групп не активна");
        }

        long currentChildrenCount = groupChildrenRepository.countByGroupIdAndEndDateIsNull(group.getId());
        if (currentChildrenCount >= group.getMaxChildrenCount()) {
            throw new ConflictException("Группа заполнена!");
        }

        boolean alreadyEnrolled = groupChildrenRepository.existsByChild_FirstNameAndChild_LastNameAndEndDateIsNull(
                dto.getFirstName(),
                dto.getLastName()
        );
        if (alreadyEnrolled) {
            throw new ConflictException("Ребенок уже зачислен в группу: " + group.getName());
        }

        Child child = childRepository.save(mapper.toEntity(dto));

        GroupChildren gc = GroupChildren.builder()
                .child(child)
                .group(group)
                .startDate(LocalDate.now())
                .price(dto.getPrice() != null ? dto.getPrice() : group.getPrice())
                .build();

        groupChildrenRepository.save(gc);

        EnrollChildDto dtoOut = mapper.toDto(child);
        dtoOut.setGroupId(group.getId());
        dtoOut.setPrice(gc.getPrice());
        return dtoOut;
    }

    @Override
    public EnrollChildDto withdrawChild(Long id, WithdrawChildDto dto) {
        GroupChildren gc = groupChildrenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Запись не найдена"));

        if (gc.getEndDate() != null) {
            throw new ConflictException("Ребенок уже отчислен из группы");
        }

        LocalDate endDate = dto.getEndDate() != null ? dto.getEndDate() : LocalDate.now();

        if (endDate.isBefore(gc.getStartDate())) {
            throw new ConflictException("Дата удаления не может быть раньше даты зачисления!");
        }

        gc.setEndDate(endDate);
        groupChildrenRepository.save(gc);

        EnrollChildDto dtoOut = mapper.toDto(gc.getChild());
        dtoOut.setGroupId(gc.getGroup().getId());
        dtoOut.setPrice(gc.getPrice());
        return dtoOut;
    }


}

