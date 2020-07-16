package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioDTO {
	private int id;
	private int numero;
	private String calle;
	private String departamento;
	private String piso;
	private UsuarioDTO propietario;
	private LocalidadDTO localidad;
	private boolean eliminado;
}
