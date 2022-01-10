package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Location;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface LocationService {
    String createNewLocation(Location newLocation);

    void editLocation(Long locationId, String locationName);

    Optional<Location> getById(Long id);

    Collection<Location> getAll();

    void delete(Location location);
}
