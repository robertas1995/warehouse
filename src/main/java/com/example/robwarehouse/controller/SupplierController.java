package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Supplier;
import com.example.robwarehouse.repository.SupplierRepo;
import com.example.robwarehouse.service.SupplierService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Data
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierRepo supplierRepo;

    @GetMapping("/createNewSupplier")
    public String createNewSupplier(Model model) {

        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);

        return "positionForm";
    }

    @PostMapping("/createNewSupplier")
    public String createNewSupplier(@Valid @ModelAttribute Supplier supplier) {
        return supplierService.createNewSupplier(supplier);
    }
}
