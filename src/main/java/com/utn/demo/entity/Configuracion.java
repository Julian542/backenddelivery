package com.utn.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	private Date horarioCierre;
	
	@Temporal(TemporalType.DATE)
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
