package br.com.yuri.rentacar.service;

import br.com.yuri.rentacar.dto.CustomerDto;
import br.com.yuri.rentacar.exception.ResourceNotFoundException;
import br.com.yuri.rentacar.mapper.CustomModelMApper;
import br.com.yuri.rentacar.model.CityModel;
import br.com.yuri.rentacar.model.CustomerModel;
import br.com.yuri.rentacar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public CustomerDto create(CustomerDto customerDto) {
        CustomerModel customerModel = CustomModelMApper.parseObject(customerDto, CustomerModel.class);
        return CustomModelMApper.parseObject(repository.save(customerModel), CustomerDto.class);
    }


    public CustomerDto findByID(long id) {
        CustomerModel customerModel = repository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Cliente nao emcontrado"));
        return CustomModelMApper.parseObject(customerModel, CustomerDto.class);
    }

    public CustomerDto update(CustomerDto customerDto){
        CustomerModel customerModel = repository.findById(customerDto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente nõa Emcontrado"));
        customerModel.setBirthDay(customerModel.getBirthDay());
        customerModel.setGender(customerModel.getGender());
        customerModel.setFullName(customerModel.getFullName());
        customerModel.setCity(CustomModelMApper.parseObject(customerDto.getCity(), CityModel.class));
        return CustomModelMApper.parseObject(repository.save(customerModel), CustomerDto.class);
    }

    public void delete(long id){
        CustomerModel customerModel = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente não Emcontrado")
        );
        repository.delete(customerModel);
    }

    public List<CustomerDto> findAll(){
        var customers = repository.findAll();
        return CustomModelMApper.parseObjectList(customers, CustomerDto.class);
    }
}