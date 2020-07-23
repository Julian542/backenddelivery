package com.utn.demo.repository;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Plato;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {
	
	/* Metodo para traer los 5 platos mas vendidos en un periodo de tiempo */
	@Query(value = "SELECT * FROM plato WHERE (fecha between ?1 AND ?2) ORDER BY cantidadVendida ASC LIMIT 5", nativeQuery = true)
	public List<Plato> platosPopulares(Date fechaDesde, Date fechaHasta);

	@Modifying
	@Transactional
	@Query("UPDATE Plato SET eliminado = true WHERE id=?1")
	public int deletePlatoById(int id);

	@Query("from Plato p where p.categoria.nombre like %?1%")
	public List<Plato> platosCategoria(String categoria);
}
