package com.utn.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import com.utn.demo.entity.Pedido;
import com.utn.demo.entity.Plato;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Query(value = "SELECT * FROM pedido WHERE fk_usuario = ?1 AND eliminado=false", nativeQuery = true)
	public List<Pedido> getAllByUser(int id);

	@Modifying
	@Transactional
	@Query("UPDATE Pedido SET eliminado = true WHERE id=?1")
	public int deletePedidoById(int id);
	
	@Transactional
	@Query(value = "SELECT * FROM pedido WHERE id=?1 AND eliminado = false",nativeQuery=true)
	public Pedido findByIdMod(int id);
	
	@Transactional
	@Query(value = "SELECT * FROM pedido WHERE eliminado = false",nativeQuery=true)
	public List<Pedido> findAllMod();
}