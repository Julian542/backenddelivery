package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuracion_EmpresaDTO {
	private int id;
	private int cuit;
	private int numeroFiscal;
	private int telefono;
	private int cantidadCocineros;
	private String nombre;
	private String email;
	private String sociedad;
	private boolean eliminado;
}
