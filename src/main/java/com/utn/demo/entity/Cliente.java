package com.utn.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@PrimaryKeyJoinColumn(name = "usuarioId")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario implements Serializable{
	
	
	@OneToMany(mappedBy = "pedidoCliente")
	private List<Pedido> pedidos;
	
	@OneToMany(mappedBy = "facturaCliente")
	private List<Factura> facturas;
}