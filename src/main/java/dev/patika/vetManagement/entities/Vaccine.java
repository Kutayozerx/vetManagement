package dev.patika.vetManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private int id;
    @Column(name = "vaccine_name")
    private String name;
    @Column(name = "vaccine_code")
    private String code;

    @Column(name = "vaccine_start_date")
    private LocalDate protectionStartDate;

    @Column(name = "vaccine_finish_date")
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", referencedColumnName = "animal_id")
    private Animal animal;
}
