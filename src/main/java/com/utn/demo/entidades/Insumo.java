package com.utn.demo.entidades;

import javax.persistence.Entity;

@Entity
public class Insumo extends Articulo{
	
	private String descripcion;
	private double precioCompra;
	private double precioVenta;
	private double stockActual;
	private double stockMaximo;
	private double stockMinimo;
	private boolean esIngrediente;
	private UnidadMedida unidadMedida;
	private String imagen;
	
}
