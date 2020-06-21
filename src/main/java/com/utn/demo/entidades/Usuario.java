package com.utn.demo.entidades;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@MappedSuperclass
public class Usuario {
	
	/* Atributos */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private String password;
	private String imagen;
	private int telefono;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Domicilio> domicilios;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
}
