package com.example.robwarehouse.service;

import com.example.robwarehouse.exception.PositionNotFoundException;
import com.example.robwarehouse.exception.PositionQuantityNotEnoughException;
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

    @Override
    public List<Position> findByProductId(Long productId) {
        return positionRepo.findByProductId(productId);
    }


    @Override
    public void recalculatePositionsForProduct(Long productId, Integer minusProduct) {
        List<Position> positions = findPositionsAndCheckNotEmpty(productId);

        Integer storageItem = positions.stream().mapToInt(Position::getQuantity).sum();
        if (storageItem < minusProduct) {
            throw new PositionQuantityNotEnoughException("Not enough storage for product: " + productId);
        }

        for (Position position : positions) {
            if (minusProduct > 0 && position.getQuantity() > 0) {

                position.setQuantity(position.getQuantity() - minusProduct);
                if (position.getQuantity() < 0) {
                    minusProduct = Math.abs(position.getQuantity());
                    position.setQuantity(0);
                } else {
                    minusProduct = 0;
                }
                editPosition(position.getId(), position);
            }
        }
    }
    @Override
    public boolean quantityIsEnough(Long productId, Integer minusProduct){
        List<Position> positions = findPositionsAndCheckNotEmpty(productId);

        Integer storageItem = positions.stream().mapToInt(Position::getQuantity).sum();
        return storageItem >= minusProduct;
    }
    @Override
    public void recalculatePositionsAndReturn(Long productId, Integer minusProduct) {
        List<Position> positions = findPositionsAndCheckNotEmpty(productId);


        for (Position position : positions) {
            if (minusProduct > 0) {

                position.setQuantity(position.getQuantity() + minusProduct);

                editPosition(position.getId(), position);
            }
            break;
        }
    }

    private List<Position> findPositionsAndCheckNotEmpty(Long productId) {
        List<Position> positions = findByProductId(productId);
        if (positions == null || positions.isEmpty()) {
            throw new PositionNotFoundException("By productId: " + productId);
        }
        return positions;
    }

}