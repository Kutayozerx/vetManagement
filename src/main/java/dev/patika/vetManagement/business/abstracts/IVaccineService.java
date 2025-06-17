package dev.patika.vetManagement.business.abstracts;

import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vetManagement.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vetManagement.dto.response.CursorResponse;
import dev.patika.vetManagement.dto.response.VaccineResponse;
import dev.patika.vetManagement.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;


public interface IVaccineService {

    ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest);
    ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest);
    ResultData<VaccineResponse> get(int id);
    boolean delete(int id);
    ResultData<CursorResponse<VaccineResponse>> cursor(int page, int pageSize);
    ResultData<List<VaccineResponse>> findByAnimalId(int animalId);
    ResultData<List<VaccineResponse>> findByProtectionDate(LocalDate startDate, LocalDate finishDate);

    List<Vaccine> findByNameAndCode(String name, String code);
}
