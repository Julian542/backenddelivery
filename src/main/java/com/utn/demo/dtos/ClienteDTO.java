package com.utn.demo.dtos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.utn.demo.entity.Domicilio;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.Pedido;

public class ClienteDTO extends UsuarioDTO{


	private List<PedidoDTO> pedidos;
	private List<FacturaDTO> facturas;
	
}
