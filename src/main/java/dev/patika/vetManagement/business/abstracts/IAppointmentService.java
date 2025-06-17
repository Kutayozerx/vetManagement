package dev.patika.vetManagement.business.abstracts;

import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vetManagement.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vetManagement.dto.response.AppointmentResponse;
import dev.patika.vetManagement.dto.response.CursorResponse;

import java.time.LocalDateTime;
import java.util.List;


public interface IAppointmentService {

    ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest);
    ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest);
    ResultData<AppointmentResponse> get(int id);
    boolean delete(int id);
    ResultData<CursorResponse<AppointmentResponse>>  cursor(int page, int pageSize);

    ResultData<List<AppointmentResponse>> findByDoctorIdAndAppointmentDateBetween(int doctorId, LocalDateTime startDate, LocalDateTime endDate);
    ResultData<List<AppointmentResponse>> findByAnimalIdAndAppointmentDateBetween(int animalId, LocalDateTime startDate, LocalDateTime endDate);
}
