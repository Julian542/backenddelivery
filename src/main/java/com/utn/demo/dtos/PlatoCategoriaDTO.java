package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatoCategoriaDTO {
	private int id;
	private String nombre;
	private String descripcion;
	private String imagen;
	private boolean eliminado;
}
