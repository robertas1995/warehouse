package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Location;
import com.example.robwarehouse.repository.LocationRepo;
import com.example.robwarehouse.service.LocationService;
import com.example.robwarehouse.service.ProductService;
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
@RequestMapping("/locations")
public class LocationController extends AbstractCrudController<Location> {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        super("locations", locationService);
        this.locationService = locationService;

    }


}