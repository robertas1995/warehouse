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
    public void editPosition(Long id, Location positionLocation, Product positionProduct, Integer positionQuantyti){
        var position = positionRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("position not found"));
        position.setLocation(positionLocation);
        position.setProduct(positionProduct);
        position.setQuantity(positionQuantyti);
        positionRepo.save(position);

    }
    @Override
    public Collection<Position> getAll(){return positionRepo.findAll();}

   @Override
    public void delete(Position position){positionRepo.delete(position);}
}
