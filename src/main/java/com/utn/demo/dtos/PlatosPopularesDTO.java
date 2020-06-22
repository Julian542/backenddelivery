package com.utn.demo.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatosPopularesDTO {
	
	private long id;
	private Date fecha;
	private PlatoDTO plato;
	private int cantidadVedida;
}
