package dev.patika.vetManagement.business.concretes;

import dev.patika.vetManagement.business.abstracts.IAvailableDateService;
import dev.patika.vetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.vetManagement.core.exception.AlreadyExistsException;
import dev.patika.vetManagement.core.exception.NotFoundException;
import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.core.utilities.Msg;
import dev.patika.vetManagement.core.utilities.ResultHelper;
import dev.patika.vetManagement.dao.AvailableDateRepo;
import dev.patika.vetManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vetManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vetManagement.dto.response.AvailableDateResponse;
import dev.patika.vetManagement.dto.response.CursorResponse;
import dev.patika.vetManagement.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, IModelMapperService modelMapper) {
        this.availableDateRepo = availableDateRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest) {

        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findByDoctorIdAndAvailableDate(availableDateSaveRequest.getDoctorId(),availableDateSaveRequest.getAvailableDate());
        if(availableDateFromDb.isPresent()){
            throw new AlreadyExistsException(Msg.ALREADY_EXIST);
        }
        AvailableDate availableDate = modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        this.availableDateRepo.save(availableDate);
        AvailableDateResponse availableDateResponse = modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
        return ResultHelper.created(availableDateResponse);
    }

    @Override
    public ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDate selectedAvailableDate = this.availableDateRepo.findById(availableDateUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        selectedAvailableDate.setAvailableDate(availableDateUpdateRequest.getAvailableDate());
        selectedAvailableDate.setDoctor(availableDateUpdateRequest.getDoctorId());

        AvailableDate availableDate = this.availableDateRepo.save(selectedAvailableDate);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
        return ResultHelper.success(availableDateResponse);
    }

    @Override
    public ResultData<AvailableDateResponse> get(int id) {
        AvailableDate availableDate = availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
        return ResultHelper.success(availableDateResponse);
    }

    @Override
    public boolean delete(int id) {
        AvailableDate availableDate = this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.availableDateRepo.delete(availableDate);
        return true;
    }

    @Override
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAll(pageable);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage.map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
        return ResultHelper.cursor(availableDateResponsePage);
    }
}
