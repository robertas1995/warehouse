package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface SupplierService {
    String createNewSupplier(Supplier createdSupplier);

    Optional<Supplier> getById(Long id);

    void delete(Supplier supplier);

    Collection<Supplier> getAll();

    void editSupplier(Long supplierId, String supplierName, String supplierEmail, String supplierTel, String supplierAddress);
}
