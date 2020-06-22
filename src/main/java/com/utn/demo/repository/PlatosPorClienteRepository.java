package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.PlatosPorCliente;

@Repository
public interface PlatosPorClienteRepository extends JpaRepository<PlatosPorCliente, Long>{

}
