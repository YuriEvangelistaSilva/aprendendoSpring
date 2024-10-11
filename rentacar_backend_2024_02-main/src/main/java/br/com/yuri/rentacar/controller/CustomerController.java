package br.com.yuri.rentacar.controller;

import br.com.yuri.rentacar.dto.CustomerDto;
import br.com.yuri.rentacar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto){
        CustomerDto customer = customerService.create(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findByid(@PathVariable(name = "id")long id){
        CustomerDto customer = customerService.findByID(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto){
        CustomerDto customerUpdate= customerService.update(customerDto);
        return  new ResponseEntity<>(customerUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") long id){
        customerService.delete(id);
        return  new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll(){
        var customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
