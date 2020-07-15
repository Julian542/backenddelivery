package com.utn.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause="eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insumo implements Serializable {

	@Column(name = "eliminado")
	private boolean eliminado;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;
	private String descripcion;
	private double precioCompra;
	private double precioVenta;
	private double stockActual;
	private double stockMaximo;
	private double stockMinimo;
	private String imagen;
	private boolean esInsumo;

	@OneToOne
	@JoinColumn(name = "fk_categoria")
	private InsumoCategoria categoria;

	@OneToOne
	@JoinColumn(name = "fk_unidad_medida")
	private UnidadMedida unidadMedida;

}
