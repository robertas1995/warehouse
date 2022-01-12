package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Location;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


public interface LocationService {
    String createNewLocation(Location newLocation);

    //TODO (LOcation location)
    void editLocation(Long locationId, String locationName);

    Optional<Location> getById(Long id);

    Collection<Location> getAll();
//TODO getBYID
    void delete(Location location);
}
