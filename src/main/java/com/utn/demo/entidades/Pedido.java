package com.utn.demo.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numeroPedido;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Estado estado;
	
	@ManyToOne
	private Cliente pedidoCliente;
	
	@OneToOne
	private Factura factura;
	
	@OneToMany (mappedBy = "pedido",cascade = CascadeType.ALL)
	List<DetallePedido> platos; /*platos o bebidas que componen este pedido*/
	
	private String tipoEnvio;
	
	private String horaEstimada;
	
	private double subtotal; /* se calcula con el precio de cada plato*/
}
