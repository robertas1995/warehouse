package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Location;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface LocationService extends CrudService<Location> {

    default Location newObject() {
        return new Location();
    }

    Long create(Location newObject);

    void update(Location update);

    Location get(Long id);

    void delete(Long id);

    Collection<Location> getAll();
}
