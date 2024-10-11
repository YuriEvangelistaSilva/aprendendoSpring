package br.com.yuri.rentacar.service;

import br.com.yuri.rentacar.dto.CityDto;
import br.com.yuri.rentacar.exception.ResourceNotFoundException;
import br.com.yuri.rentacar.mapper.CustomModelMApper;
import br.com.yuri.rentacar.model.CityModel;
import br.com.yuri.rentacar.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public CityDto create(CityDto cityDto){
        CityModel city= CustomModelMApper.parseObject(cityDto, CityModel.class);
        return CustomModelMApper.parseObject(repository.save(city), CityDto.class);
    }

    public CityDto findById(long id){
        CityModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cidade não encontrada")
        );
        return CustomModelMApper.parseObject(found, CityDto.class);
    }

    public  CityDto update(CityDto cityDto){
        CityModel found = repository.findById(cityDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Cidade não Encontrada"));
        found.setName(cityDto.getName());
        found.setState(cityDto.getState());
        return CustomModelMApper.parseObject(repository.save(found), CityDto.class);
    }

    public void delete(long id){
        CityModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cidade não emcontrada"));
        repository.delete(found);
    }

    public List<CityDto> findAll(){
        var list = repository.findAll();
        return CustomModelMApper.parseObjectList(list, CityDto.class);
    }
    public List<CityDto> findByName(String name){
        var cities = repository.findByNameContainsIgnoreCaseOrderByName(name);
        return CustomModelMApper.parseObjectList(cities, CityDto.class);
    }
    public List<CityDto> findByState(String state){
        var cities = repository.findByStateContainsIgnoreCaseOrderByStateAscNameAsc(state);
        return CustomModelMApper.parseObjectList(cities, CityDto.class);
    }

}
