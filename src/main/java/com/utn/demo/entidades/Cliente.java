package com.utn.demo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {
	
	
	@OneToMany(mappedBy = "pedidoCliente")
	private List<Pedido> pedidos;
	
	@OneToMany(mappedBy = "facturaCliente")
	private List<Factura> facturas;
}
