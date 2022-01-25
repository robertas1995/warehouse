package com.example.robwarehouse.repository;

import com.example.robwarehouse.model.Location;
import com.example.robwarehouse.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {


}
