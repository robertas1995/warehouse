package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Location;
import com.example.robwarehouse.repository.LocationRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService{

    private final LocationRepo locationRepo;

    @Override
    public Long create(Location newLocation){
        Location newLoca = new Location();
        newLoca.setLocationName(newLocation.getLocationName());
        locationRepo.save(newLoca);
     return newLoca.getId();
    }

    @Override
    public void update(Location update){
        var editLocation = locationRepo.findById(update.getId()).orElseThrow(()-> new EntityNotFoundException("Location not found"));
        editLocation.setLocationName(update.getLocationName());
        locationRepo.save(editLocation);
    }
    @Override
    public Location get(Long id){ return locationRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Location not found"));}

    @Override
    public Collection<Location> getAll(){return locationRepo.findAll();}

    @Override
    public void delete(Long id){locationRepo.deleteById(id);}





}


