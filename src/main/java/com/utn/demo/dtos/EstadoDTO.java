package com.utn.demo.dtos;

import com.utn.demo.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {
	private long id;
	private PedidoDTO pedido;
	private String estadoPedido; 
}	
