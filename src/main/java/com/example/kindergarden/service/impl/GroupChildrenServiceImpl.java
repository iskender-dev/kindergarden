package com.example.kindergarden.service.impl;

import com.example.kindergarden.exception.ConflictException;
import com.example.kindergarden.exception.LogicException;
import com.example.kindergarden.exception.NotFoundException;
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
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GroupChildrenServiceImpl implements GroupChildrenService {
    private final GroupChildrenRepository groupChildrenRepository;
    private final ChildRepository childRepository;
    private final GroupRepository groupRepository;

    public GroupChildrenServiceImpl(GroupChildrenRepository groupChildrenRepository, ChildRepository childRepository, GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
        this.childRepository = childRepository;
        this.groupChildrenRepository = groupChildrenRepository;
    }

    @Transactional
    @Override
    public ResponseEntity<GlobalResponse> enrollChild(EnrollChildDto enrollChildDto) {

        Group group = groupRepository.findById(enrollChildDto.getGroupId()).orElseThrow(() ->
                new NotFoundException("Не удалось найти группу с id - " + enrollChildDto.getGroupId()));

        if (!group.getGroupCategory().isActive()) {
            throw new ConflictException("Категория групп не активна");
        }
        long activeChildrenCount = groupChildrenRepository.countByChildIdAndEndDateIsNull((group.getId());
        if (activeChildrenCount >= group.getMaxChildrenCount()) {
            throw new ConflictException("Группа полная");
        }

        Child child = childRepository.findOrCreate(enrollChildDto.getFirstName(),enrollChildDto.getLastName(),
                enrollChildDto.getPatronymic(),enrollChildDto.getDateOfBirth());

        GroupChildren activeEnrollment = groupChildrenRepository.findByChildIdAndEndDateIsNull(child.getId());


        if(activeEnrollment != null){
            throw new ConflictException("Ребенок уже зачислен в группу: "+ activeEnrollment.getGroup().getName());
        }
        GroupChildren enrollment = new GroupChildren();
        enrollment.setChild(child);
        enrollment.setGroup(group);
        enrollment.setStartDate(LocalDate.now().minusMonths(1));
        enrollment.setPrice(enrollChildDto.getPrice() != null? enrollChildDto.getPrice(): group.getPrice());

        GroupChildren savedEnrollment = groupChildrenRepository.save(enrollment);

        GlobalResponse response = GlobalResponse.created(savedEnrollment);

        return ResponseEntity.status(201).body(response);
    }

    @Transactional
    @Override
    public ResponseEntity<GlobalResponse> withdrawChild(Long id, WithdrawChildDto withdrawChildDto) {
        GroupChildren enrollment = groupChildrenRepository.findById(id).orElseThrow(() ->
                new NotFoundException("запись ребенка в группе не найдена"));

        if (enrollment.getEndDate() != null)
            throw new LogicException("Ребенок уже отчислен из группы");

        LocalDate endDate;
        if (withdrawChildDto.getEndDate() != null) {
            endDate = withdrawChildDto.getEndDate();
        }else
            endDate = LocalDate.now();

        if (endDate.isBefore(enrollment.getStartDate()))
            throw new ConflictException("Дата удаления не  может быть раньше даты зачисления!");
        enrollment.setEndDate(endDate);
        GroupChildren updatedEnrollment = groupChildrenRepository.save(enrollment);

        GlobalResponse response = GlobalResponse.success(updatedEnrollment);
        return ResponseEntity.ok(response);
    }
}
