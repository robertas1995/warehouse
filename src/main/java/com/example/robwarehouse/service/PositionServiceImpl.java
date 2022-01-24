package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Position;
import com.example.robwarehouse.repository.PositionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepo positionRepo;

    @Override
    public Optional<Position> getById(Long id) {
        return positionRepo.findById(id);
    }

    @Override
    public Long createNewPosition(Position newPosition) {
        Position position = new Position();
        position.setLocation(newPosition.getLocation());
        position.setProduct(newPosition.getProduct());
        position.setQuantity(newPosition.getQuantity());
        positionRepo.save(position);
        return position.getId();
    }

    @Override
    public Long editPosition(Long id, Position editPosition) {
        Position position = positionRepo.getById(id);
        position.setLocation(editPosition.getLocation());
        position.setProduct(editPosition.getProduct());
        position.setQuantity(editPosition.getQuantity());
        positionRepo.save(position);
        return position.getId();

    }

    @Override
    public Collection<Position> getAll() {
        return positionRepo.findAll();
    }

    @Override
    public void delete(Position position) {
        positionRepo.delete(position);
    }

    @Override
    public Collection<Position> getLow() {

        List<Position> needToOrder = getAll().stream().filter(h -> h.getQuantity() <= 5).collect(Collectors.toList());

        return needToOrder;
    }


}

