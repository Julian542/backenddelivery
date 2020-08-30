package com.utn.demo.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT COUNT(email) FROM tabla_usuario WHERE email = ?1", nativeQuery = true)
	public String existeEmail(String email);

	@Query(value = "SELECT * FROM usuario WHERE email = ?1 and eliminado=false", nativeQuery = true)
	public Optional<Usuario> buscarPorEmail(String email);

	@Transactional
	@Query(value = "SELECT * FROM usuario WHERE rol = 'cocinero' and eliminado=false", nativeQuery = true)
	public List<Usuario> getCocineros();

	@Modifying
	@Transactional
	@Query("UPDATE Usuario SET eliminado = true WHERE id=?1")
	public int deleteUsuarioById(int id);

	@Transactional
	@Query(value = "SELECT * FROM usuario WHERE id=?1 AND eliminado = false", nativeQuery = true)
	public Usuario findByIdMod(int id);

	@Transactional
	@Query(value = "SELECT * FROM usuario WHERE eliminado = false", nativeQuery = true)
	public List<Usuario> findAllMod();
	
	@Transactional
	@Query(value = "SELECT * FROM usuario WHERE rol=?1 AND eliminado = false", nativeQuery = true)
	public List<Usuario> traerPorRol(String rol);
}
