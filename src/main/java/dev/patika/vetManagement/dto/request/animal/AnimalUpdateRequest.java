package dev.patika.vetManagement.dto.request.animal;

import dev.patika.vetManagement.entities.Customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {

    @Positive(message = "Id degeri pozitif sayi olmak zorunda")
    private int id;
    @NotNull(message = "Hayvan adi bos veya null olamaz")
    private String name;
    @NotNull(message = "Hayvan turu bos veya null olamaz")
    private String species;
    @NotNull(message = "Hayvan cinsi bos veya null olamaz")
    private String breed;
    @NotNull(message = "Hayvan cinsiyeti bos veya null olamaz")
    private String gender;
    @NotNull(message = "Hayvan rengi bos veya null olamaz")
    private String colour;
    private LocalDate dateOfBirth;
    private Customer customer;

}
