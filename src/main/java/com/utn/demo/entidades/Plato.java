package com.utn.demo.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class Plato extends Articulo{
	
	private String nombre;
	private float precioVenta;
	private float precioCosto; /* esto se calcula el precio de los insumos*/
	private int tiempoPreparacion;
	private String descripcion;
	private String imagen;
	
	@OneToOne (cascade = CascadeType.ALL)
	private List<DetallePlato> receta;
	
	@ManyToOne
	private CategoriaPlato categoria;
	
	@ManyToOne
	private SubcategoriaPlato subcategoria;
}
