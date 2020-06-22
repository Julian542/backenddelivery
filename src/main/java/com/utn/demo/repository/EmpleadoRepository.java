package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	
	
	//public String getRol(String email);
	/* Este metodo consulta el rol de un empleado, tal vez no haga falta */ 
}
