package dev.patika.vetManagement.dto.request.appointment;

import dev.patika.vetManagement.entities.Animal;
import dev.patika.vetManagement.entities.Doctor;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "Id degeri pozitif olmali")
    private int id;
    private LocalDateTime appointmentDate;
    private Animal animalId;
    private Doctor doctorId;
}
