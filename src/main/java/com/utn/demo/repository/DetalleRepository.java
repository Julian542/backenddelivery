package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

import com.utn.demo.entity.Detalle;

public interface DetalleRepository extends JpaRepository<Detalle, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Detalle SET eliminado = true WHERE id=?1")
	public int deleteDetalleById (int id);
	
}
