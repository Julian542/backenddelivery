package com.utn.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

	/*Este método puede servir para a futuro hacer una consulta que traiga todas las facturas
	De algun cliente en específico*/
	@Query(value = "SELECT * FROM factura WHERE fk_usuario = ?1", nativeQuery = true)
	public List<Factura> getAllByUser(int id);

	/*Metodo para traer todas las facturas en un periodo de tiempo*/
	@Query(value = "SELECT * FROM factura WHERE (fecha between ?1 AND ?2)", nativeQuery = true)
	public List<Factura> getFacturasByDate(Date fechaDesde, Date fechaHasta);
	
	@Modifying
	@Transactional
	@Query("UPDATE Factura SET eliminado = true WHERE id=?1")
	public int deleteFacturaById (int id);
}
