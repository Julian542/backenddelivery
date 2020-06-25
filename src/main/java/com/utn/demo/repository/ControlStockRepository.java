package com.utn.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.ControlStock;

@Repository
public interface ControlStockRepository extends JpaRepository<ControlStock, Long> {
	@Query(value = "SELECT * FROM Insumos WHERE stockMinimo < stockActual", nativeQuery = true)
	public List<ControlStock> buscarAll();
}
