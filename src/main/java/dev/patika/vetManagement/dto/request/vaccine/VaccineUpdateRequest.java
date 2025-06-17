package dev.patika.vetManagement.dto.request.vaccine;

import dev.patika.vetManagement.entities.Animal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {
    @Positive(message = "Id degeri pozitif olmali")
    private int id;
    @NotNull(message = "Asi adi bos olamaz")
    private String name;
    @NotNull(message = "Asi kodu bos olamaz")
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Animal animal;

}