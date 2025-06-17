package dev.patika.vetManagement.api;

import dev.patika.vetManagement.business.abstracts.IAvailableDateService;
import dev.patika.vetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.vetManagement.core.result.Result;
import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.core.utilities.ResultHelper;
import dev.patika.vetManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vetManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vetManagement.dto.response.AvailableDateResponse;
import dev.patika.vetManagement.dto.response.CursorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/available_dates")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;

    public AvailableDateController(IAvailableDateService availableDateService, IModelMapperService modelMapper) {
        this.availableDateService = availableDateService;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        return this.availableDateService.save(availableDateSaveRequest);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        return availableDateService.cursor(page, pageSize);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") int id){
        return availableDateService.get(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){

        return availableDateService.update(availableDateUpdateRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }
}
