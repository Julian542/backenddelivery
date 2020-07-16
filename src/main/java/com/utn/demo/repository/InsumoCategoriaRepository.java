package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import com.utn.demo.entity.InsumoCategoria;

public interface InsumoCategoriaRepository extends JpaRepository<InsumoCategoria, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE InsumoCategoria SET eliminado = true WHERE id=?1")
	public int deleteInsumoCategoriaById(int id);
}
