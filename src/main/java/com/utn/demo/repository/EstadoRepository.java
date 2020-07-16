package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import com.utn.demo.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Estado SET eliminado = true WHERE id=?1")
	public int deleteEstadoById(int id);
}