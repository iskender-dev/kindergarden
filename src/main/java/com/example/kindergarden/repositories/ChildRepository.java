package com.example.kindergarden.repositories;

import com.example.kindergarden.models.Child;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {
    Child findChildByLastNameandFirstNameAndPatronymicAndBirthDate(String firstName, String lastName, String patronymic, LocalDate birthDate);

    @Transactional
    default Child findOrCreate(String firstName, String lastName, String patronymic, LocalDate dateOfBirth){
        Child child = findChildByLastNameandFirstNameAndPatronymicAndBirthDate(firstName, lastName, patronymic, dateOfBirth);
        if (child != null){
            return child;
        }
        Child newChild = new Child();
        newChild.setFirstName(firstName);
        newChild.setLastName(lastName);
        newChild.setPatronymic(patronymic);
        newChild.setBirthDate(dateOfBirth);
        return  save(newChild);
    }
}
