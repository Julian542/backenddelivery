package com.utn.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import com.utn.demo.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	@Query(value = "SELECT * FROM pedido WHERE fk_usuario = ?1", nativeQuery = true)
	public List<Pedido> getAllByUser(int id);

	@Modifying
	@Transactional
	@Query("UPDATE Pedido SET eliminado = true WHERE id=?1")
	public int deletePedidoById(int id);
}