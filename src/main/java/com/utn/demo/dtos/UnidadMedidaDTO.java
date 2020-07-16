package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadMedidaDTO {
	private int id;
	private String nombre;
	private String abreviatura;
	private boolean eliminado;
}
