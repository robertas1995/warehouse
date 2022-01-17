package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Location;
import com.example.robwarehouse.model.Position;
import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.repository.PositionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepo positionRepo;

    @Override
    public Optional<Position> getById(Long id){return positionRepo.findById(id);}

    @Override
    public String createNewPosition(Position newPosition){
        Position position = new Position();
        position.setLocation(newPosition.getLocation());
        position.setProduct(newPosition.getProduct());
        position.setQuantity(newPosition.getQuantity());
        positionRepo.save(position);
        return "redirect:/positions/positionList";
    }

    @Override
    public String editPosition(Long id, Position editPosition){
        Position position = positionRepo.getById(id);
        position.setLocation(editPosition.getLocation());
        position.setProduct(editPosition.getProduct());
        position.setQuantity(editPosition.getQuantity());
        positionRepo.save(position);
        return "redirect:/positions/positionList";

    }
    @Override
    public Collection<Position> getAll(){return positionRepo.findAll();}

   @Override
    public void delete(Position position){positionRepo.delete(position);}
}
