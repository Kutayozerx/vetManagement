package dev.patika.vetManagement.business.abstracts;

import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.dto.request.doctor.DoctorSaveRequest;
import dev.patika.vetManagement.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.vetManagement.dto.response.CursorResponse;
import dev.patika.vetManagement.dto.response.DoctorResponse;


public interface IDoctorService {

    ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest);

    ResultData<DoctorResponse> get(int id);

    void delete(int id);

    ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize);

    DoctorResponse save(DoctorSaveRequest doctorSaveRequest);
}
