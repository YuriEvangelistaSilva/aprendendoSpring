package br.com.yuri.rentacar.controller;

import br.com.yuri.rentacar.dto.CityDto;
import br.com.yuri.rentacar.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")

public class CityController {

    @Autowired
    private CityService service;

    @PostMapping
    public ResponseEntity<CityDto> create(@RequestBody CityDto cityDto){
        CityDto city = service.create(cityDto);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> findyById(@PathVariable(name = "id")long id){
        CityDto city = service.findById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CityDto> update(@RequestBody  CityDto cityDto){
        CityDto city = service.update(cityDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> findAll(){
        var list = service.findAll();
        return new ResponseEntity<List<CityDto>>(list, HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CityDto>> findByName(@PathVariable(name = "name") String name){
        var cities = service.findByName(name);
        return new ResponseEntity<List<CityDto>>(cities, HttpStatus.OK);
    }
    @GetMapping("/find/state/{state}")
    public ResponseEntity<List<CityDto>> findByState(@PathVariable(name = "state") String state){
        var cities = service.findByState(state);
        return new ResponseEntity<List<CityDto>>(cities, HttpStatus.OK);
}}
