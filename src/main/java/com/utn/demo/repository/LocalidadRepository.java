package com.utn.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Localidad;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Localidad SET eliminado = true WHERE id=?1")
	public int deleteLocalidadById(int id);

	@Transactional
	@Query(value = "SELECT * FROM Localidad WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public Localidad findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM Localidad WHERE eliminado = false", nativeQuery = true)
	public List<Localidad> findAllMod();
}
