package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatosPorClienteRepository extends JpaRepository<PlatosPorClienteRepository, Long>{

}
