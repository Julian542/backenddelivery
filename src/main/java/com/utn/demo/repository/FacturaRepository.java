package com.utn.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

	@Query(value = "SELECT * FROM factura WHERE fk_usuario = ?1 and eliminado=false", nativeQuery = true)
	public List<Factura> getAllByUser(int id);

	@Query(value = "SELECT * FROM factura WHERE CAST(fecha AS DATE) >= CAST(?1 AS DATE) AND CAST(fecha AS DATE) <= CAST(?2 AS DATE) AND eliminado = FALSE", nativeQuery = true)
	public List<Factura> getFacturasByDate(String fechaDesde, String fechaHasta);

	@Modifying
	@Transactional
	@Query("UPDATE Factura SET eliminado = true WHERE id=?1")
	public int deleteFacturaById(int id);

	@Transactional
	@Query(value = "SELECT * FROM Factura WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public Factura findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM Factura WHERE eliminado = false", nativeQuery = true)
	public List<Factura> findAllMod();
}
