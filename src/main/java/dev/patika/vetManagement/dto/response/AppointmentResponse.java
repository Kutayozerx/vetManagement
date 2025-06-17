package dev.patika.vetManagement.dto.response;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    @Positive(message = "Id degeri pozitif olmali")
    private int id;
    private LocalDateTime appointmentDate;
    private int animalId;
    private int doctorId;
}
