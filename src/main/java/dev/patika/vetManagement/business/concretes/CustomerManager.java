package dev.patika.vetManagement.business.concretes;

import dev.patika.vetManagement.business.abstracts.ICustomerService;
import dev.patika.vetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.vetManagement.core.exception.AlreadyExistsException;
import dev.patika.vetManagement.core.exception.NotFoundException;
import dev.patika.vetManagement.core.result.ResultData;
import dev.patika.vetManagement.core.utilities.Msg;
import dev.patika.vetManagement.core.utilities.ResultHelper;
import dev.patika.vetManagement.dao.CustomerRepo;
import dev.patika.vetManagement.dto.request.customer.CustomerSaveRequest;
import dev.patika.vetManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vetManagement.dto.response.AnimalResponse;
import dev.patika.vetManagement.dto.response.CursorResponse;
import dev.patika.vetManagement.dto.response.CustomerResponse;
import dev.patika.vetManagement.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public CustomerManager(CustomerRepo customerRepo, IModelMapperService modelMapper) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;

    }
    @Override
    public ResultData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest) {

        Optional<Customer> customerFromDb = customerRepo.findByNameAndPhoneAndMail(customerSaveRequest.getName(), customerSaveRequest.getPhone(), customerSaveRequest.getMail());
        if(customerFromDb.isPresent()){
            throw new AlreadyExistsException(Msg.ALREADY_EXIST);
        }
        Customer customer = modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerRepo.save(customer);

        modelMapper.forResponse().map(customer, CustomerResponse.class);
        return ResultHelper.created(this.modelMapper.forResponse().map(customerSaveRequest, CustomerResponse.class));
    }

    @Override
    public ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest) {
        Customer selectedCustomer = this.customerRepo.findById(customerUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        selectedCustomer.setName(customerUpdateRequest.getName());
        selectedCustomer.setMail(customerUpdateRequest.getMail());
        selectedCustomer.setPhone(customerUpdateRequest.getPhone());
        selectedCustomer.setAddress(customerUpdateRequest.getAddress());
        selectedCustomer.setCity(customerUpdateRequest.getCity());

        Customer updatedCustomer = this.customerRepo.save(selectedCustomer);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(updatedCustomer, CustomerResponse.class);
        return ResultHelper.success(customerResponse) ;
    }

    @Override
    public ResultData<CustomerResponse> get(int id) {
        Customer customer = this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer, CustomerResponse.class);
        return ResultHelper.success(customerResponse);
    }

    @Override
    public ResultData<List<CustomerResponse>> findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
        String lowerCase = name.toLowerCase();
        List<Customer> filteredCustomers = customerRepo.findByName(lowerCase);
        List<CustomerResponse> customerResponses = filteredCustomers.stream()
                .map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(customerResponses);

    }

    @Override
    public ResultData<List<AnimalResponse>> findAnimalByCustomerId(int customerId) {
        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new RuntimeException(Msg.NOT_FOUND));
        if (customer.getAnimalList() != null && !customer.getAnimalList().isEmpty()) {
            List<AnimalResponse> animalResponses = customer.getAnimalList().stream()
                    .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                    .collect(Collectors.toList());
            return ResultHelper.success(animalResponses);
        } else {
            throw new NotFoundException(Msg.NOT_FOUND);
        }
    }



    @Override
    public boolean delete(int id) {
        Customer customer = this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.customerRepo.delete(customer);
        return true;
    }

    @Override
    public ResultData<CursorResponse<CustomerResponse>> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Page<Customer> customerPage = this.customerRepo.findAll(pageable);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }
}
