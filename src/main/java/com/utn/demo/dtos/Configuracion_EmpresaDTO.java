package com.utn.demo.dtos;

import java.util.Date;
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
	private Date horarioCierre;
	private Date horarioApertura;
	private String nombreEmpresa;
	private String emailEmpresa;
	private String sociedad;
	private String paginaWeb;
	private boolean eliminado;
}
