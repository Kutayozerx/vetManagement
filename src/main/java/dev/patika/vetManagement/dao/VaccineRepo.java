package dev.patika.vetManagement.dao;

import dev.patika.vetManagement.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Integer> {

    List<Vaccine> findByAnimalId(int animalId);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate);

    List<Vaccine> findByNameAndCode(String name, String code);
}
