package com.utn.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DetallePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Pedido pedido; /* pedido al que pertenece*/
	
	private double subtotal; /* se calcula sumando el precio del plato x la cantidad*/
	
	private int cantidad;
	
	private Articulo articulo; /* el articulo puede ser insumo (bebida) o plato*/
	
}
