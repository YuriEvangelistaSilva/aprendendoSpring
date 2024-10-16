package br.com.yuri.rentacar.controller;

import br.com.yuri.rentacar.dto.CustomerDto;
import br.com.yuri.rentacar.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag( name = "Customers", description = "Endpint para operaçoes emvolvendo Costumers")
@RestController
@RequestMapping("/Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria ou cadastra um novo Customer (cliente)", tags = {"Customer"} responces = {
            @ApiResponse(description = "CREATED", responseCode = "201", content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerDto.class)
            )})
    })
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
