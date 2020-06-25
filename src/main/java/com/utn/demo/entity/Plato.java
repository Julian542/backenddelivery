package com.utn.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@PrimaryKeyJoinColumn(name = "articuloId")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plato extends Articulo implements Serializable{
	
	private String nombre;
	private float precioVenta;
	private float precioCosto; /* esto se calcula el precio de los insumos*/
	private int tiempoPreparacion;
	private String descripcion;
	private String imagen;
	private int cantidadVendida;
	
	@OneToMany (cascade = CascadeType.ALL)
	private List<DetallePlato> receta;
	
	@ManyToOne
	private CategoriaPlato categoriaPlato;
	
	@ManyToOne
	private SubcategoriaPlato subcategoriaPlato;
}
