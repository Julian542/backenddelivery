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

	@Query(value = "SELECT * FROM insumo WHERE es_insumo=?1 and eliminado=false", nativeQuery = true)
	public List<Insumo> getAllNoInsumos(boolean esInsumo);

	@Query(value = "SELECT * FROM insumo WHERE fk_categoria=?1 and eliminado=false", nativeQuery = true)
	public List<Insumo> getAllporCategoria(int id);

	@Query(value = "SELECT * FROM insumo WHERE fk_categoria=?1 and es_insumo=false and eliminado=false", nativeQuery = true)
	public List<Insumo> buscarPorCategoriaNoInsumo(int id);

	@Query(value = "SELECT * FROM insumo WHERE stock_minimo > stock_actual AND fk_categoria=?1 and eliminado=false", nativeQuery = true)
	public List<Insumo> getInsumosWithLowStock(int id);
	
	@Query(value = "SELECT * FROM insumo WHERE stock_minimo > stock_actual AND eliminado=false", nativeQuery = true)
	public List<Insumo> getInsumosWithLowStockSinCategoria();

	@Modifying
	@Transactional
	@Query("UPDATE Insumo SET eliminado = true WHERE id=?1")
	public int deleteInsumoById(int id);

	@Transactional
	@Query(value = "SELECT * FROM insumo WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public Insumo findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM insumo WHERE eliminado = false", nativeQuery = true)
	public List<Insumo> findAllMod();
}
