package com.utn.demo.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecaudacionesDTO {
	private Date fechaDesde;
	private Date fechaHasta;
	private double montoNeto;
	private double gastos;
	private double ganancias;
	private boolean eliminado;
}
