package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import javax.transaction.Transactional;
import com.utn.demo.entity.PlatoCategoria;

public interface PlatoCategoriaRepository extends JpaRepository<PlatoCategoria, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE PlatoCategoria SET eliminado = true WHERE id=?1")
	public int deletePlatoCategoriaById(int id);

	@Transactional
	@Query(value = "SELECT * FROM plato_categoria WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public PlatoCategoria findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM plato_categoria WHERE eliminado = false", nativeQuery = true)
	public List<PlatoCategoria> findAllMod();
}
