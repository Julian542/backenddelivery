package com.utn.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Domicilio;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
	
	@Query("from Domicilio s where s.usuario.id like ?1")
	List<Domicilio> buscarPorUsuario(long id);
}
