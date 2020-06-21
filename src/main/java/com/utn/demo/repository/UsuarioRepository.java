package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Articulo;

@Repository
public interface UsuarioRepository extends JpaRepository<Articulo, Long> {

}
