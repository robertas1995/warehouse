package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Supplier;
import com.example.robwarehouse.repository.SupplierRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepo supplierRepo;

    @Override
    public String createNewSupplier(Supplier createdSupplier) {

        Supplier newSupplier = new Supplier();
        newSupplier.setName(createdSupplier.getName());
        newSupplier.setEmail(createdSupplier.getEmail());
        newSupplier.setTel(createdSupplier.getTel());
        newSupplier.setAddress(createdSupplier.getAddress());
        supplierRepo.save(newSupplier);

        return "redirect:/supplier";
    }

    @Override
    public Optional<Supplier> getById(Long id) {
        return supplierRepo.findById(id);
    }


    @Override
    public void delete(Supplier supplier) {
        supplierRepo.delete(supplier);
    }

    @Override
    public Collection<Supplier> getAll() {
        return supplierRepo.findAll();
    }

    @Override
    public void editSupplier(Long supplierId, String supplierName, String supplierEmail, String supplierTel, String supplierAddress) {
        var supplier = supplierRepo.findById(supplierId).orElseThrow(() -> new EntityNotFoundException("supplier not found"));
        supplier.setName(supplierName);
        supplier.setEmail(supplierEmail);
        supplier.setTel(supplierTel);
        supplier.setAddress(supplierAddress);
        supplierRepo.save(supplier);
    }
}


