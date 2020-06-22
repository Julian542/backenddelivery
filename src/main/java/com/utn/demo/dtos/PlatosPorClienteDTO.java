package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatosPorClienteDTO {
	private long id;
	private String email;
	private String nombre;
	private String apellido;
	private String nombrePlato;
	private int cantidad;
	private String periodo;
}
