package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
