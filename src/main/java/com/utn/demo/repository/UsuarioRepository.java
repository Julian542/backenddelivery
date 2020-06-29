package com.utn.demo.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	// ¿Existe este usuario?
	@Query(value = "SELECT COUNT(email) FROM tabla_usuario WHERE email = ?1", nativeQuery = true)
	public String existeEmail(
			String email); /*
							 * Si devuelve null, se registra un usuario, caso contrario, ingresa al sistema
							 */

	// Traer el usuario por su mail
	@Query(value = "SELECT * FROM usuario WHERE email = ?1", nativeQuery = true)
	public Optional<Usuario> buscarPorEmail(String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE Usuario SET eliminado = true WHERE id=?1")
	public int deleteUsuarioById (int id);
}
