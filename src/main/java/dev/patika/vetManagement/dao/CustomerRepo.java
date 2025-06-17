package dev.patika.vetManagement.dao;

import dev.patika.vetManagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    List<Customer> findByName(String name);
    List<Customer> findAnimalById(int animalId);

    Optional<Customer> findByNameAndPhoneAndMail(String name, String phone, String mail);

}