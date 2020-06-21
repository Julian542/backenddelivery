package com.utn.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario {
	
	
	@OneToMany(mappedBy = "pedidoCliente")
	private List<Pedido> pedidos;
	
	@OneToMany(mappedBy = "facturaCliente")
	private List<Factura> facturas;
}
