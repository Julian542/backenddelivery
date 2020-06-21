package com.utn.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Pedido pedido;
	
	private String estadoPedido; 
	/* puede tomar los siguientes valores: 
	   PENDIENTE, CONFIRMADO, DENEGADO, EN PREPARACION, COCINADO, EN DELIVERY, FACTURADO*/
}
