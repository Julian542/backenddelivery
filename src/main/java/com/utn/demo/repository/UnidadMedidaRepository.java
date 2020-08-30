package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import javax.transaction.Transactional;
import com.utn.demo.entity.UnidadMedida;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE UnidadMedida SET eliminado = true WHERE id=?1")
	public int deleteUnidadMedidaById(int id);

	@Transactional
	@Query(value = "SELECT * FROM unidad_medida WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public UnidadMedida findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM unidad_medida WHERE eliminado = false", nativeQuery = true)
	public List<UnidadMedida> findAllMod();
}
