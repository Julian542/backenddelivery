package com.utn.demo.controller;

import java.util.Date;

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

import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.service.InsumoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
@RequestMapping(path = "api/v1/insumo")
public class InsumoController {

	private InsumoService insumoService;

	public InsumoController(InsumoService insumoService) {
		this.insumoService = insumoService;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/esInsumo/{esInsumo}")
	@Transactional
	public ResponseEntity getAllNoInsumos(@PathVariable boolean esInsumo) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}

	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable int id) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getOne(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}

	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody InsumoDTO dto) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");

		}

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable int id, @RequestBody InsumoDTO dto) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.update(dto, id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {

		boolean borrado = insumoService.delete(id);
		if(borrado) {
			return ResponseEntity.status(HttpStatus.OK).body("'message':'Eliminado'");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("'message':'Error al eliminar'");
		}
	}
	
	@GetMapping("/stocks")
	@Transactional
	public ResponseEntity getStocks() {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getStocks());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}

	}
}
