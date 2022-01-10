package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Customer;
import com.example.robwarehouse.model.Location;
import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.repository.LocationRepo;
import com.example.robwarehouse.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Controller
@Slf4j
@Data
@RequestMapping("/location")
public class LocationController {

    private final LocationRepo locationRepo;
    private final LocationService locationService;

    @GetMapping("/createNewLocation")
    public String createNewLocation(Model model){
        Location location = new Location();
        model.addAttribute("location", location);
        return "location";
    }
    @PostMapping("/createNewLocation")
    public String createNewLocation(@Valid @ModelAttribute Location location, Product product ){

        return locationService.createNewLocation(location);
    }

    @GetMapping("/Edit{id}")
    public String editLocation(@PathVariable Long id, Model model){
        model.addAttribute("locationId", id);
        var location = locationRepo.getById(id);
        model.addAttribute("editLocation", location);
        return "/location" +id;
    }

    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id){
        Optional<Location> optionalLocation = this.locationService.getById(id);
        Location location = optionalLocation.get();
        this.locationService.delete(location);
        return "redirect:/location";
    }
    @GetMapping("/{id}")
    public String getLocation(@PathVariable Long id, Model model){
        Optional<Location> optionalLocation = this.locationService.getById(id);
        Location location = optionalLocation.get();
        model.addAttribute("location", location);
        return "location";
    }
    @GetMapping("/all")
    public String getAll(Model model){
        Collection<Location> locationCollection = this.locationService.getAll();
        model.addAttribute("location", locationCollection);

        return "locationList";
    }
}