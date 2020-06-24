package com.utn.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Pedido implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numeroPedido;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Estado estado;
	
	@ManyToOne
	private Cliente pedidoCliente;
	
	@OneToOne
	private Factura factura;
	
	@OneToOne
	private FormaPago formaPago;
	
	@OneToMany (mappedBy = "pedido",cascade = CascadeType.ALL)
	List<DetallePedido> detallePedido; /*platos o bebidas que componen este pedido*/
	
	private String tipoEnvio;
	
	private String horaEstimada;
	
	private double subtotal; /* se calcula con el precio de cada plato*/

	@ManyToOne
	private Cocina cocina;
}
