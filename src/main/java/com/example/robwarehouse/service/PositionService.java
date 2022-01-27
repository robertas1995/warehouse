package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Position;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface PositionService {
    Optional<Position> getById(Long id);

    Long createNewPosition(Position newPosition);

    Long editPosition(Long id, Position editPosition);

    Collection<Position> getAll();

    void delete(Position position);


    Collection<Position> getLow();

    List<Position> findByProductId(Long productId);

    void recalculatePositionsForProduct(Long productId, Integer minusProduct);

    void recalculatePositionsAndReturn(Long productId, Integer minusProduct);
}
