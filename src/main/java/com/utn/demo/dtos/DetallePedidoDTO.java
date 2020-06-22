package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDTO {
	
	private long id;
	private PedidoDTO pedido;
	private double subtotal;
	private int cantidad;
	private InsumoDTO insumo;
	private PlatoDTO plato;

}
