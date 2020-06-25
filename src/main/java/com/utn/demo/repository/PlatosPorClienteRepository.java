package com.utn.demo.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Pedido;
import com.utn.demo.entity.PlatosPorCliente;

@Repository
public interface PlatosPorClienteRepository extends JpaRepository<PlatosPorCliente, Long>{
	@Query(value = "SELECT * FROM pedido WHERE (fecha between ?1 AND ?2) AND (pedido_cliente_usuario_id = ?3)", nativeQuery = true)
	public List<Pedido> buscarPedidos(Date primerDia, Date ultimoDia, long idUsuario);
	
}
