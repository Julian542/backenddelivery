package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePlatoDTO {
	private int id;
	private float cantidad;
	private UnidadMedidaDTO unidadMedida;
	private InsumoDTO ingrediente;
	private boolean eliminado;
	private PlatoDTO plato;
}
