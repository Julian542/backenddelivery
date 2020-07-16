package com.utn.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Where;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause = "eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int numero;
	private String calle;
	private String departamento;
	private String piso;
	@ManyToOne
	private Usuario propietario;
	@OneToOne
	private Localidad localidad;
	@Column(name = "eliminado")
	private boolean eliminado;
}
