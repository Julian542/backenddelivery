package com.utn.demo.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause="eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

	/* Atributos */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private String password;
	private String imagen;
	private int telefono;

	private boolean esCliente; // este campo sirve para que si el frontend al consultarlo, es true, muestra
								// pantalla cliente, caso contrario, muestra pantalla empleado

	@OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
	private List<Domicilio> domicilios;

	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	// atributos propios de Empleado
	private String Rol;
	
	@Column(name = "eliminado")
	private boolean eliminado;
}
