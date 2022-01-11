package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Location;
import com.example.robwarehouse.repository.LocationRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
@Data
public class LocationServiceImpl implements LocationService{

    private final LocationRepo locationRepo;

    @Override
    public String createNewLocation(Location newLocation){
        Location newLoca = new Location();
        newLoca.setLocationName(newLocation.getLocationName());
        locationRepo.save(newLoca);
     return "redirect:/location/all";
    }

    @Override
    public void editLocation(Long locationId, String locationName){
        var location = locationRepo.findById(locationId).orElseThrow(()-> new EntityNotFoundException("Location not found"));
        location.setLocationName(locationName);
        locationRepo.save(location);
    }
    @Override
    public Optional<Location> getById(Long id){ return locationRepo.findById(id);}

    @Override
    public Collection<Location> getAll(){return locationRepo.findAll();}

    @Override
    public void delete(Location location){locationRepo.delete(location);}





}


