package dev.patika.vetManagement.business.abstracts;

import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.vetManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vetManagement.dto.response.AnimalResponse;
import dev.patika.vetManagement.dto.response.CursorResponse;

import java.util.List;

public interface IAnimalService {

    // Animal save(Animal animal);
    ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);
    ResultData<AnimalResponse> get(int id);
    ResultData<CursorResponse<AnimalResponse>> cursor(int page, int pageSize);
    boolean delete(int id);
    ResultData<List<AnimalResponse>> findByName(String name);
    ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);


}
