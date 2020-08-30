package com.utn.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Domicilio;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {

	@Query("from Domicilio s where s.propietario.id like ?1 and s.eliminado = false")
	List<Domicilio> buscarPorUsuario(int id);

	@Modifying
	@Transactional
	@Query("UPDATE Domicilio SET eliminado = true WHERE id=?1")
	public int deleteDomicilioById(int id);

	@Transactional
	@Query(value = "SELECT * FROM Domicilio WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public Domicilio findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM Domicilio WHERE eliminado = false", nativeQuery = true)
	public List<Domicilio> findAllMod();
}
