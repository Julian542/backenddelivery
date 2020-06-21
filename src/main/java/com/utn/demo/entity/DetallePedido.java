package com.utn.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Pedido pedido; /* pedido al que pertenece*/
	
	private double subtotal; /* se calcula sumando el precio del plato x la cantidad*/
	
	private int cantidad;
	
	private Articulo articulo; /* el articulo puede ser insumo (bebida) o plato*/
	
}
