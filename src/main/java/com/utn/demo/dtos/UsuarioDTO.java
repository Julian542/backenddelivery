package com.utn.demo.dtos;

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
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private String password;
	private String imagen;
	private int telefono;
	private List<DomicilioDTO> domicilios;
	private Date fechaNacimiento;
	private boolean esCliente;
	private String Rol;
	private boolean eliminado;
}
