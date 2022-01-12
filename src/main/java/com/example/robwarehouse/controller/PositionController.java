package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Location;
import com.example.robwarehouse.model.Position;
import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.repository.LocationRepo;
import com.example.robwarehouse.repository.PositionRepo;
import com.example.robwarehouse.repository.ProductRepo;
import com.example.robwarehouse.service.PositionService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.*;

@Controller
@Slf4j
@Data
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;
    private final PositionRepo positionRepo;
    private final LocationRepo locationRepo;
    private final ProductRepo productRepo;

    @GetMapping("/createNewPosition")
    public String createNewPosition(Model model){
        Position position = new Position();
        model.addAttribute("position", position);
        List<Location> locations = locationRepo.findAll();
        model.addAttribute("locations",locations);
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "positionForm";
    }
    @PostMapping("/createNewPosition")
    public String createNewPosition(@Valid @ModelAttribute Position position,
                                    @ModelAttribute Location location,
                                    @ModelAttribute Product product, Model model){

        return positionService.createNewPosition(position);
    }
    @GetMapping("/editPosition/{id}")
    public String editPosition(@PathVariable Long id, Model model) {
        var position = positionService.getById(id);
        if (position.isPresent()) {
            model.addAttribute("editPosition", position.get());
            List<Location> location = locationRepo.findAll();
            model.addAttribute("locations", location);
            List<Product> product = productRepo.findAll();
            model.addAttribute("products", product);
        }
        return "editPosition";
    }


    @PostMapping("/editPosition/{id}")
    public String editPosition(@PathVariable Long id,
                               @ModelAttribute Position position,
                               @ModelAttribute Location location,
                               @ModelAttribute Product product){

        return positionService.editPosition(id,position);
    }

    @GetMapping("/positionList")
    public String getAll(Model model){
        Collection<Position> positions = this.positionService.getAll();
        model.addAttribute("positions",positions);

        return "positionList";
    }
    @GetMapping("/position/{id}")
    public String getPosition(@PathVariable Long id, Model model){
        Optional<Position> optionalPosition = this.positionService.getById(id);
        Position position = optionalPosition.get();
        model.addAttribute("position", position);
        return "position";
    }
    @GetMapping("/delete/{id}")
    public  String deleteLocation(@PathVariable Long id){
        Optional<Position> optionalPosition = this.positionService.getById(id);
        Position position = optionalPosition.get();
        this.positionService.delete(position);
        return "redirect:/positions/positionList";
    }


}
