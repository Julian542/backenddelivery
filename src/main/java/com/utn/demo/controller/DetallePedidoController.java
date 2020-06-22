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

import com.utn.demo.dtos.DetallePedidoDTO;
import com.utn.demo.service.DetallePedidoService;

@RestController
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.DELETE , RequestMethod.PUT, RequestMethod.POST})
@RequestMapping(path = "")
public class DetallePedidoController {
protected final DetallePedidoService detallePedidoService;
	
	public DetallePedidoController(DetallePedidoService detallePedidoService) {
		this.detallePedidoService = detallePedidoService;
	}
	
	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detallePedidoService.findAll());
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
	}
	
	/*@GetMapping("/query/{id}")
	@Transactional
	public ResponseEntity buscarPorIdP(@PathVariable long id ) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(detallePedidoService.buscarPorIdP(id));
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
	}*/
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable long id){
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(detallePedidoService.findById(id));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
		
		
		
	}
	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody DetallePedidoDTO dto){
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(detallePedidoService.save(dto));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable long id, @RequestBody DetallePedidoDTO dto){
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(detallePedidoService.update(id, dto));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity post(@PathVariable long id){
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(detallePedidoService.delete(id));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
		
	}
}
