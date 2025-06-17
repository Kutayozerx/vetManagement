package dev.patika.vetManagement.dao;

import dev.patika.vetManagement.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate,Integer> {
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(int doctorId, LocalDate date);


}
