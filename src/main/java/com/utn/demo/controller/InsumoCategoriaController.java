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
import com.utn.demo.dtos.InsumoCategoriaDTO;
import com.utn.demo.service.InsumoCategoriaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
@RequestMapping(path = "api/v1/insumo/categoria")
public class InsumoCategoriaController {

	private InsumoCategoriaService insumoCategoriaService;

	public InsumoCategoriaController(InsumoCategoriaService insumoCategoriaService) {
		this.insumoCategoriaService = insumoCategoriaService;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity<Object> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoCategoriaService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/NoInsumo/")
	@Transactional
	public ResponseEntity<Object> getAllNoInsumo() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoCategoriaService.getAllNoInsumo());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoCategoriaService.getOne(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<Object> post(@RequestBody InsumoCategoriaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoCategoriaService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> put(@PathVariable int id, @RequestBody InsumoCategoriaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoCategoriaService.update(dto, id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		boolean borrado = insumoCategoriaService.delete(id);
		if (borrado) {
			return ResponseEntity.status(HttpStatus.OK).body(borrado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("'message':'Error al eliminar'");
		}
	}
}
