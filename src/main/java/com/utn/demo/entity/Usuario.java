package com.utn.demo.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Where;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause = "eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int dni;
	private long telefono;
	private boolean esCliente;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String rol;
	private Date fechaNacimiento;
	@OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
	private List<Domicilio> domicilios;
	@Column(name = "eliminado")
	private boolean eliminado;
}
