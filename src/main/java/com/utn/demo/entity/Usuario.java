package com.utn.demo.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tabla_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
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
	private boolean esCliente; //este campo sirve para que si el frontend al consultarlo, es true, muestra pantalla cliente, caso contrario, muestra pantalla empleado
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Domicilio> domicilios;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
}
