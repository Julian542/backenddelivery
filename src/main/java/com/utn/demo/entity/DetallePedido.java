package com.utn.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_posee_plato")
	private Plato plato;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_posee_insumo")
	private Insumo insumo;
	
}
