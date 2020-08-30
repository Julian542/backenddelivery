package com.utn.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Plato;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Plato SET eliminado = true WHERE id=?1")
	public int deletePlatoById(int id);

	@Query("from Plato p where p.categoria.nombre like %?1%")
	public List<Plato> platosCategoria(String categoria);

	@Transactional
	@Query(value = "SELECT * FROM plato WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public Plato findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM plato WHERE eliminado = false", nativeQuery = true)
	public List<Plato> findAllMod();

	@Transactional
	@Query(value = "SELECT * FROM plato WHERE fk_categoria = ?1", nativeQuery = true)
	public List<Plato> findPlatoPorCategoria(int id);
}
