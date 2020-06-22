package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControlStockDTO {
	
	private long id;
	private String nombreInsumo;
	private double stockMinimo;
	private double stockActual;
}
