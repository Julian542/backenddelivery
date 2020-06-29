package com.utn.demo.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.utn.demo.dtos.UsuarioDTO;
import com.utn.demo.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
@RequestMapping(path = "api/v1/usuario")
public class UsuarioController {

	protected final UsuarioService servicio;

	public UsuarioController(UsuarioService servicio) {
		this.servicio = servicio;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");

		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");

		}
	}

	@GetMapping("/exist/{email}")
	@Transactional
	public ResponseEntity existeEmail(@PathVariable String email) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.existeEmail(email));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");

		}
	}

	@GetMapping("/searchbyemail/{email}")
	@Transactional
	public ResponseEntity buscarPorEmail(@PathVariable String email) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.existeEmail(email));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");

		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody UsuarioDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");

		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable int id, @RequestBody UsuarioDTO dto) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {

		boolean borrado = servicio.delete(id);
		if(borrado) {
			return ResponseEntity.status(HttpStatus.OK).body("'message':'Eliminado'");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("'message':'Error al eliminar'");
		}
	}
}
