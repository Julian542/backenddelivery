package com.utn.demo.dtos;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;

import com.utn.demo.entity.Localidad;
import com.utn.demo.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioDTO {
	private long id;
	private ClienteDTO cliente;
	private EmpleadoDTO empleado;
	private LocalidadDTO localidad;
	private String calle;
	private int numero;
	private String departamento; 
	private String piso;
	
}
