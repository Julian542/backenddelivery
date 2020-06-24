package com.utn.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//metodo buscar cliente por email 
	@Query(value = "SELECT * FROM cliente WHERE email = ?1", nativeQuery = true)
	public Optional<Cliente> buscarPorEmail(String email);
	
}
