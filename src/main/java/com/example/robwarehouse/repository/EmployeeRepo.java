package com.example.robwarehouse.repository;


import com.example.robwarehouse.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository <Employee, Long> {


}
