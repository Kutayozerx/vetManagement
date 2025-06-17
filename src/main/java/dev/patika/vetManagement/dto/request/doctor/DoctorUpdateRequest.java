package dev.patika.vetManagement.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {

    @Positive(message = "Id degeri pozitif olmali")
    private int id;
    @NotNull(message = "Ad bos olamaz")
    private String name;
    @NotNull(message = "Telefon bos olamaz")
    private String phone;
    @NotNull(message = "Mail bos olamaz")
    private String mail;
    @NotNull(message = "Adres bos olamaz")
    private String address;
    @NotNull(message = "Sehir bos olamaz")
    private String city;
}
