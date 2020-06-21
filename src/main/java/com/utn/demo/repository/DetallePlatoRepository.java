package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.DetallePlato;

@Repository
public interface DetallePlatoRepository extends JpaRepository<DetallePlato, Long> {

}
