package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Recaudaciones;

@Repository
public interface RecaudacionesRepository extends JpaRepository<Recaudaciones, Long> {

}
