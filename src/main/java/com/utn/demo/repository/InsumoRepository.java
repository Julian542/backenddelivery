package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

}
