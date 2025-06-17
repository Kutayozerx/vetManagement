package dev.patika.vetManagement.dto.request.availableDate;

import dev.patika.vetManagement.entities.Doctor;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {

    @Positive(message = "Id degeri poszitif olmali")
    private int id;
    private LocalDate availableDate;
    private Doctor doctorId;
}
