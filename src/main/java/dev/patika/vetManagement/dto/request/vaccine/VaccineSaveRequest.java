package dev.patika.vetManagement.dto.request.vaccine;

import dev.patika.vetManagement.entities.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {

    @NotNull(message = "Asi adi bos olamaz")
    private String name;
    @NotNull(message = "Asi kodu bos olamaz")
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Animal animal;
}