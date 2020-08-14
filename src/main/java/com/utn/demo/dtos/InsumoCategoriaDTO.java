package com.utn.demo.dtos;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsumoCategoriaDTO {
	private int id;
	private String nombre;
	private String descripcion;
	private boolean eliminado;
	private boolean es_insumo;
}
