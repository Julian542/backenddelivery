package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import javax.transaction.Transactional;
import com.utn.demo.entity.InsumoCategoria;

public interface InsumoCategoriaRepository extends JpaRepository<InsumoCategoria, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE InsumoCategoria SET eliminado = true WHERE id=?1")
	public int deleteInsumoCategoriaById(int id);

	@Transactional
	@Query(value = "SELECT * FROM Insumo_Categoria WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public InsumoCategoria findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM Insumo_Categoria WHERE eliminado = false", nativeQuery = true)
	public List<InsumoCategoria> findAllMod();

	@Transactional
	@Query(value = "SELECT * FROM Insumo_Categoria WHERE eliminado = false and es_insumo=false", nativeQuery = true)
	public List<InsumoCategoria> findAllNoInsumo();
}
