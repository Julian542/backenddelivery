package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Contado;

@Repository
public interface ContadoRepository extends JpaRepository<Contado, Long>{

}
