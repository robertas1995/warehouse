package com.example.robwarehouse.repository;

import com.example.robwarehouse.model.Order;
import com.example.robwarehouse.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepo extends JpaRepository<Position, Long> {
}
