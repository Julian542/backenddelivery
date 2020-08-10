package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDTO {
	private int id;
	private int cantidad;
	private PlatoDTO plato;
	private InsumoDTO insumo;
	private PedidoDTO pedido;
	private boolean eliminado;
}
