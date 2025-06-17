package dev.patika.vetManagement.business.abstracts;

import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vetManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vetManagement.dto.response.AvailableDateResponse;
import dev.patika.vetManagement.dto.response.CursorResponse;


public interface IAvailableDateService {

    ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest);
    ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest);
    ResultData<AvailableDateResponse> get(int id);
    boolean delete(int id);
    ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize);
}
