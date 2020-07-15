package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioDTO {
	
	private int id;
	private UsuarioDTO propietario;
	private LocalidadDTO localidad;
	private String calle;
	private int numero;
	private String departamento;
	private String piso;
	private boolean eliminado;

}
