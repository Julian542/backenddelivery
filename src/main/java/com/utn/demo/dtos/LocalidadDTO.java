package com.utn.demo.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalidadDTO {
	
	private long id;
	private List<DomicilioDTO> domicilio;	
	private String nombre;
}
