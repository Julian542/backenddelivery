package com.utn.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause="eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatoCategoria implements Serializable {

	@Column(name = "eliminado")
	private boolean eliminado;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;
	private String descripcion;

}
