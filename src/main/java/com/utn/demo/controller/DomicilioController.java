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
import com.utn.demo.dtos.DomicilioDTO;
import com.utn.demo.service.DomicilioService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
@RequestMapping(path = "api/v1/domicilio")
public class DomicilioController {

	protected final DomicilioService servicio;

	public DomicilioController(DomicilioService servicio) {
		this.servicio = servicio;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity<Object> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/buscarporUsuario/{id}")
	@Transactional
	public ResponseEntity<Object> buscarporUsuario(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarporUsuario(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<Object> post(@RequestBody DomicilioDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> put(@PathVariable int id, @RequestBody DomicilioDTO dto) {
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
		if (borrado) {
			return ResponseEntity.status(HttpStatus.OK).body(borrado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("'message':'Error al eliminar'");
		}
	}
}
