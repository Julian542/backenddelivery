package com.utn.demo.dtos;

import java.util.Date;
import java.util.List;

public class UsuarioDTO {
	

	private long id;
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private String password;
	private String imagen;
	private int telefono;
	private List<DomicilioDTO> domicilios;
	private Date fechaNacimiento;
}
