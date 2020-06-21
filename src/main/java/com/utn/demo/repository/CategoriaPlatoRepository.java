package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.CategoriaPlato;

@Repository
public interface CategoriaPlatoRepository extends JpaRepository<CategoriaPlato, Long> {

}
