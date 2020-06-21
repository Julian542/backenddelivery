package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.SubcategoriaPlato;

@Repository
public interface SubcategoriaPlatoRepository extends JpaRepository<SubcategoriaPlato, Long>{

}
