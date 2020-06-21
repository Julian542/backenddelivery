package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.ControlStock;

@Repository
public interface ControlStockRepository extends JpaRepository<ControlStock, Long> {

}
