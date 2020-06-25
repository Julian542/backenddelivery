package com.utn.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Plato;
import com.utn.demo.entity.PlatosPopulares;

@Repository
public interface PlatosPopularesRepository extends JpaRepository<PlatosPopulares, Long> {
	@Query(value = "SELECT * FROM plato ORDER BY cantidadVendida ASC LIMIT 5", nativeQuery = true)
	public List<Plato> platosPopulares();
}
