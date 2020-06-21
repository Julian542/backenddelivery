package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Plato;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {

}
