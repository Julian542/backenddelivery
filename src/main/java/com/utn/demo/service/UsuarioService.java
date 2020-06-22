package com.utn.demo.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	protected final UsuarioRepository repositorio;
	
	public UsuarioService(UsuarioRepository repositorio) {
		this.repositorio = repositorio;
	}
	
	@Transactional
	public String existeEmail(String email) {

		String validacion = repositorio.existeEmail(email);
		if(validacion != "0") {
			return "true";
		}else {
			return "false";
		}
	}
	//este metodo existe para consultar la existencia de un usuario en la base de datos, 
}
