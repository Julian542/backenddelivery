package com.utn.demo.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.utn.demo.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
@RequestMapping(path = "api/v1/usuario")
public class UsuarioController {
	
	protected final UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	@GetMapping("/queryemail/{email}")
	@Transactional
	public ResponseEntity existeEmail(@PathVariable String email) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.existeEmail(email));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
	
}
