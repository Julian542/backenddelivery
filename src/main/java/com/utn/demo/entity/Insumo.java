package com.utn.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insumo extends Articulo implements Serializable{
	
	private String descripcion;
	private double precioCompra;
	private double precioVenta;
	private double stockActual;
	private double stockMaximo;
	private double stockMinimo;
	private boolean esIngrediente;
	private UnidadMedida unidadMedida;
	private String imagen;
	
	@ManyToOne
	private SubcategoriaInsumo subcategoriaInsumo;
	
}
