package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Location;
import com.example.robwarehouse.model.Position;
import com.example.robwarehouse.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface PositionService {
    Optional<Position> getById(Long id);


    String createNewPosition(Position newPosition);

    void editPosition(Long id, Location positionLocation, Product positionProduct, Integer positionQuantyti);

    Collection<Position> getAll();

    void delete(Position position);
}