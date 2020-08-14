package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import javax.transaction.Transactional;
import com.utn.demo.entity.Detalle;
import com.utn.demo.entity.DetallePlato;

public interface DetalleRepository extends JpaRepository<Detalle, Integer> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Detalle SET eliminado = true WHERE id=?1")
	public int deleteDetalleById(int id);
	
	@Transactional
	@Query("from Detalle where pedido_id like ?1 AND eliminado = false")
	public List<Detalle> buscarPorPedido(int id);
	
	@Transactional
	@Query("from Detalle where pedido_id like ?1 AND fk_plato like ?2 AND eliminado = false")
	public List<Detalle> buscarPorPlato(int id, int id2);
	
	@Transactional
	@Query("from Detalle where pedido_id like ?1 AND fk_insumo like ?2 AND eliminado = false")
	public List<Detalle> buscarPorInsumo(int id, int id2);
	
	@Transactional
	@Query(value = "SELECT * FROM Detalle WHERE id=?1 AND eliminado = false",nativeQuery=true)
	public Detalle findByIdMod(int id);
	
	@Transactional
	@Query(value = "SELECT * FROM Detalle WHERE eliminado = false",nativeQuery=true)
	public List<Detalle> findAllMod();
}
