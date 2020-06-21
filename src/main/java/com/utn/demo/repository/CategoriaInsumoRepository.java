package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.CategoriaInsumo;

@Repository
public interface CategoriaInsumoRepository extends JpaRepository<CategoriaInsumo, Long> {

}
