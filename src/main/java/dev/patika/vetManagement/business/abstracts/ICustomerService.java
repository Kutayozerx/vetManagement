package dev.patika.vetManagement.business.abstracts;

import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.dto.request.customer.CustomerSaveRequest;
import dev.patika.vetManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vetManagement.dto.response.AnimalResponse;
import dev.patika.vetManagement.dto.response.CursorResponse;
import dev.patika.vetManagement.dto.response.CustomerResponse;

import java.util.List;


public interface ICustomerService {

    // ResultData<Customer> save(Customer customer);

    ResultData<CustomerResponse> save (CustomerSaveRequest customerSaveRequest);
    ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest);
    ResultData<CustomerResponse> get(int id);
    boolean delete(int id);
    ResultData<CursorResponse<CustomerResponse>> cursor(int page, int pageSize);
    ResultData<List<CustomerResponse>> findByName(String name);

    ResultData<List<AnimalResponse>> findAnimalByCustomerId(int customerId);

}
