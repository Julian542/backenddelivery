package com.utn.demo.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.Recaudaciones;

@Repository
public interface RecaudacionesRepository extends JpaRepository<Recaudaciones, Long> {
	@Query(value = "SELECT * FROM factura WHERE (fecha between ?1 AND ?2) AND (factura_cliente_usuario_id = ?3)", nativeQuery = true)
	public List<Factura> buscarFacturas(Date fechaAnterior, Date fechaHoy, long idUsuario);
	/*@Query(value = "SELECT fecha, total, factura_cliente_usuario_id, fk_posee_pedido FROM factura WHERE (fecha between ?1 AND ?2) AND (factura_cliente_usuario_id = ?3)", nativeQuery = true)
	public List<Factura> buscarFacturas(Calendar fechaAnterior, Calendar fechaHoy, long idUsuario);*/
}
