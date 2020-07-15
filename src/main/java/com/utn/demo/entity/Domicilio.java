package com.utn.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause="eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Usuario propietario;

	@OneToOne
	private Localidad localidad; /* Las Heras, Junin, Godoy Cruz */

	private String calle;
	private int numero;
	private String departamento; /* Departamento 'A' por ejemplo */
	private String piso;
	@Column(name = "eliminado")
	private boolean eliminado;

}
