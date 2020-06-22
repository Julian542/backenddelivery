package com.utn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utn.demo.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value="SELECT COUNT(email) FROM tabla_usuario WHERE email = ?1", nativeQuery = true)
	public String existeEmail(String email); /* Si devuelve null, se registra un usuario, caso contrario, ingresa al sistema*/

	
}
