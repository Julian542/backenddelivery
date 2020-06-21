package com.utn.demo.dtos;

import com.utn.demo.entity.Articulo;

public class DetallePedidoDTO {
	
	private long id;
	private PedidoDTO pedido;
	private double subtotal;
	private int cantidad;
	private InsumoDTO insumo;
	private PlatoDTO plato;

}
