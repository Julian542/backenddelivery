package com.utn.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Domicilio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Localidad localidad;
	
	private String calle;
	private int numero;
	private String departamento; /* departamento A por ejemplo*/
	private String piso;
	
}
