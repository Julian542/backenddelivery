package com.utn.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer> {

	@Query(value = "SELECT * FROM insumo WHERE insumo_esinsumo=?1", nativeQuery = true)
	public List<Insumo> getAllNoInsumos(boolean esInsumo);

	/* Metodo para traer los insumos con poco Stock */
	@Query(value = "SELECT * FROM insumo WHERE stockMinimo < stockActual", nativeQuery = true)
	public List<Insumo> getInsumosWithLowStock();

	@Modifying
	@Transactional
	@Query("UPDATE Insumo SET eliminado = true WHERE id=?1")
	public int deleteInsumoById(int id);
}
