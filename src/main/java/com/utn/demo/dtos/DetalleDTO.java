package com.utn.demo.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDTO {
	private int id;
	private int cantidad;
	private LocalDate fecha;
	private PlatoDTO plato;
	private InsumoDTO insumo;
	private PedidoDTO pedido;
	private boolean eliminado;
}
