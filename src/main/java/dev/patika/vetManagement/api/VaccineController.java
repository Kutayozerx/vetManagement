package dev.patika.vetManagement.api;

import dev.patika.vetManagement.business.abstracts.IVaccineService;
import dev.patika.vetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.vetManagement.core.result.Result;
import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.core.utilities.ResultHelper;
import dev.patika.vetManagement.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vetManagement.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vetManagement.dto.response.CursorResponse;
import dev.patika.vetManagement.dto.response.VaccineResponse;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;

    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper) {
        this.vaccineService = vaccineService;

    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest){
        return vaccineService.save(vaccineSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return vaccineService.cursor(page, pageSize);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") int id){
        return vaccineService.get(id);
    }


    @GetMapping("/animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getAnimalById(@PathVariable("animalId") int animalId) {
        return this.vaccineService.findByAnimalId(animalId);
    }

    @GetMapping("/protection-dates")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getByProtectionEndDateBetween(
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finishDate) {
        return this.vaccineService.findByProtectionDate(startDate, finishDate);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){
        return vaccineService.update(vaccineUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }
}
