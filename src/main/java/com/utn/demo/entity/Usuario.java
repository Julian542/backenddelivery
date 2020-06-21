package com.utn.demo.entity;

import java.io.Serializable;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable{
	
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
