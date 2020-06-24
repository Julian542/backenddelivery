package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Cocina;

@Repository
public interface CocinaRepository extends JpaRepository<Cocina, Long>{

}
