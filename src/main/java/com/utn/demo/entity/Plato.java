package com.utn.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	private CategoriaPlato categoriaPlato;
	
	@ManyToOne
	private SubcategoriaPlato subcategoriaPlato;
}
