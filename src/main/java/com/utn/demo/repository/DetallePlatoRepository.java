package com.utn.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.DetallePlato;

@Repository
public interface DetallePlatoRepository extends JpaRepository<DetallePlato, Integer> {

	@Query(value = "SELECT * FROM Detalle_Plato WHERE plato_id=?1 AND eliminado = false", nativeQuery = true)
	public List<DetallePlato> getAllByUser(int id);

	@Modifying
	@Transactional
	@Query("UPDATE DetallePlato SET eliminado = true WHERE id=?1")
	public int deleteDetallePlatoById(int id);

	@Transactional
	@Query(value = "SELECT * FROM Detalle_Plato WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public DetallePlato findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM Detalle_Plato WHERE eliminado = false", nativeQuery = true)
	public List<DetallePlato> findAllMod();

	@Transactional
	@Query(value = "SELECT * FROM Detalle_Plato WHERE plato_id=?1 AND eliminado = false", nativeQuery = true)
	public List<DetallePlato> findAllPorPlato(int id);
}