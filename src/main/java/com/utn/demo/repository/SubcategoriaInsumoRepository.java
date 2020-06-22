package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.SubcategoriaInsumo;

@Repository
public interface SubcategoriaInsumoRepository extends JpaRepository<SubcategoriaInsumo, Long> {

}