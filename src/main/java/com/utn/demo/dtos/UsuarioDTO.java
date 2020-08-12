package com.utn.demo.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private int id;
	private int dni;
	private long telefono;
	private boolean esCliente;
	private Date fechaNacimiento;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String imagen;
	private String rol;
	private List<DomicilioDTO> domicilios = new ArrayList<DomicilioDTO>();
	private boolean eliminado;
}
