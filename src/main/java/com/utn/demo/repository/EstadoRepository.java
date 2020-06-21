package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
