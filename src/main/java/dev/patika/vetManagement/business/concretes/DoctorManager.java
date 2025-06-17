package dev.patika.vetManagement.business.concretes;

import dev.patika.vetManagement.business.abstracts.IDoctorService;
import dev.patika.vetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.vetManagement.core.exception.AlreadyExistsException;
import dev.patika.vetManagement.core.exception.NotFoundException;
import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.core.utilities.Msg;
import dev.patika.vetManagement.core.utilities.ResultHelper;
import dev.patika.vetManagement.dao.DoctorRepo;
import dev.patika.vetManagement.dto.request.doctor.DoctorSaveRequest;
import dev.patika.vetManagement.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.vetManagement.dto.response.CursorResponse;
import dev.patika.vetManagement.dto.response.DoctorResponse;
import dev.patika.vetManagement.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;

    public DoctorManager(DoctorRepo doctorRepo, IModelMapperService modelMapper) {
        this.doctorRepo = doctorRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public ResultData<DoctorResponse>  update(DoctorUpdateRequest doctorUpdateRequest) {
        Doctor selectedDoctor = this.doctorRepo.findById(doctorUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        selectedDoctor.setName(doctorUpdateRequest.getName());
        selectedDoctor.setCity(doctorUpdateRequest.getCity());
        selectedDoctor.setAddress(doctorUpdateRequest.getAddress());
        selectedDoctor.setMail(doctorUpdateRequest.getMail());
        selectedDoctor.setPhone(doctorUpdateRequest.getPhone());

        Doctor updatedDoctor = this.doctorRepo.save(selectedDoctor);
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(updatedDoctor, DoctorResponse.class);
        return ResultHelper.success(doctorResponse);
    }

    @Override
    public ResultData<DoctorResponse> get(int id) {
        Doctor doctor = this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(doctor, DoctorResponse.class);
        return ResultHelper.success(doctorResponse);
    }

    @Override
    public void delete(int id) {

        this.doctorRepo.delete(this.doctorRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND)));
    }

    @Override
    public ResultData<CursorResponse<DoctorResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Doctor> doctorPage = this.doctorRepo.findAll(pageable);
        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    @Override
    public DoctorResponse save(DoctorSaveRequest doctorSaveRequest) {
        Optional<Doctor> doctorFromDb = doctorRepo.findByNameAndPhoneAndMail(doctorSaveRequest.getName(), doctorSaveRequest.getPhone(), doctorSaveRequest.getMail());
        if(doctorFromDb.isPresent()){
            throw new AlreadyExistsException(Msg.ALREADY_EXIST);
        }
        Doctor doctor = modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorRepo.save(doctor);
        return this.modelMapper.forResponse().map(doctor, DoctorResponse.class);
    }
}

