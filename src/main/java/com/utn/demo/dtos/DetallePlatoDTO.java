package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePlatoDTO {
	private long id;
	private InsumoDTO ingrediente;
	private int cantidad;
}
