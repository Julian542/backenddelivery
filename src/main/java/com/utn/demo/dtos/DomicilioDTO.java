package com.utn.demo.dtos;

import javax.persistence.ManyToOne;

import com.utn.demo.entity.Localidad;
import com.utn.demo.entity.Usuario;

public class DomicilioDTO {
	private long id;
	private ClienteDTO cliente;
	private EmpleadoDTO empleado;
	private LocalidadDTO localidad;
	private String calle;
	private int numero;
	private String departamento; 
	private String piso;
	
}
