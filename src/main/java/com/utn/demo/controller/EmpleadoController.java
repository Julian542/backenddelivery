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

import com.utn.demo.dtos.EmpleadoDTO;
import com.utn.demo.service.EmpleadoService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping(path = "api/v1/empleado")
public class EmpleadoController {
	
	protected final EmpleadoService servicio;
	
	public EmpleadoController( EmpleadoService servicio) {
		this.servicio = servicio;
	}
	
	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.getAll());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.getOne(id));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
	}
	
	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody EmpleadoDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.save(dto));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable long id, @RequestBody EmpleadoDTO dto){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
		}
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable long id){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(servicio.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
}
