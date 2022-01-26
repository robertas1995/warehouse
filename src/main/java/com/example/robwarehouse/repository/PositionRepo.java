package com.example.robwarehouse.repository;

import com.example.robwarehouse.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepo extends JpaRepository<Position, Long> {

    List<Position> findByProductId(Long productId);
}
