package com.utn.demo.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utn.demo.entity.Configuracion_Empresa;

@Repository
public interface Configuracion_EmpresaRepository extends JpaRepository<Configuracion_Empresa, Integer> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Configuracion_Empresa SET eliminado = true WHERE id=?1")
	public int deleteConfiguracionById(int id);
}
