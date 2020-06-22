package com.utn.demo.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionDTO {
	private long id;
	private Date horarioCierre;
	private Date horarioApertura;
	private int cantidadCocineros;
	private String nombreEmpresa;
	private String emailEmpresa;
	private int cuit;
	private int numeroFiscal;
	private int telefono;
	private String sociedad;
	private String paginaWeb;
}
