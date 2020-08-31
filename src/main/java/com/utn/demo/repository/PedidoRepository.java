package com.utn.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import com.utn.demo.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM pedido WHERE fk_usuario = ?1 AND eliminado=false", nativeQuery = true)
	public List<Pedido> getAllByUser(int id);

	@Query(value = "SELECT * FROM pedido WHERE fk_usuario = ?1 AND CAST(fecha AS DATE) >= CAST(?2 AS DATE) AND CAST(fecha AS DATE) <= CAST(?3 AS DATE) AND eliminado = FALSE", nativeQuery = true)
	public List<Pedido> getPedidosPorUsuario(int id, String fechaDesde, String fechaHasta);

	@Query(value = "SELECT * FROM pedido WHERE fk_usuario = ?1 AND estado_id = ?2 AND eliminado=false", nativeQuery = true)
	public List<Pedido> getPedidoEstado(int id, int id2);
	
	@Query(value = "SELECT * FROM pedido WHERE estado_id != 6 AND eliminado=false", nativeQuery = true)
	public List<Pedido> getAllPedidosMenosFacturados();

	@Modifying
	@Transactional
	@Query("UPDATE Pedido SET eliminado = true WHERE id=?1")
	public int deletePedidoById(int id);

	@Transactional
	@Query(value = "SELECT * FROM pedido WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public Pedido findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM pedido WHERE eliminado = false", nativeQuery = true)
	public List<Pedido> findAllMod();
}
