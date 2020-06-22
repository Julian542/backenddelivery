package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFacturaDTO {
	
	private long id;
	private int cantidad;
	private long facturaRelacionada;
	
}
