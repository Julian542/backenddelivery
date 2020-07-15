package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;

import com.utn.demo.entity.UnidadMedida;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE UnidadMedida SET eliminado = true WHERE id=?1")
	public int deleteUnidadMedidaById (int id);
}
