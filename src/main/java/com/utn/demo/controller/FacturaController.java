package com.utn.demo.controller;

import java.util.List;

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

import com.utn.demo.dtos.FacturaDTO;
import com.utn.demo.exceptions.StatusException;
import com.utn.demo.service.FacturaService;

@RestController
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.DELETE , RequestMethod.PUT, RequestMethod.POST})
@RequestMapping(path = "")
public class FacturaController {

	private FacturaService FacturaService;

	public FacturaController(FacturaService FacturaService){
		this.FacturaService = FacturaService;
	}

	@GetMapping("/")
	@CrossOrigin(origins = "*")
	public List<FacturaDTO> getAll() {
		
		return ResponseEntity.status(200).body(FacturaService.getAll()).getBody();
		
	}

	@GetMapping("/{id}")
	@CrossOrigin(origins = "*")
	public FacturaDTO getOne(@PathVariable int id) {
				
		return ResponseEntity.status(200).body(FacturaService.getOne(id)).getBody();
		
	}


	@PostMapping("/")
	@CrossOrigin(origins = "*")
	public ResponseEntity save(@RequestBody FacturaDTO t) {
		
		FacturaDTO temp = FacturaService.save(t);		
		
		try {
			
			if(temp.getId() != 0) {
				return ResponseEntity.status(201).body(temp);
			}
			else {
				throw new StatusException("Bad request. Please check the values", 400);
			}
			
		} catch (StatusException e) {
			
			return e.getResponseStatus();
			
		}
		
	}

	@PutMapping("/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity update(@RequestBody FacturaDTO t, @PathVariable int id) {
		
		FacturaDTO temp = FacturaService.update(t, id);
		
		try {
			
			if(temp.getId() != 0) {
				return ResponseEntity.status(201).body(temp);
			}
			else {
				throw new StatusException("Bad request. Please check the values", 400);
			}
			
		} catch (StatusException e) {
			
			return e.getResponseStatus();
			
		}
		
	}

	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity delete(@PathVariable int id) {
	
		boolean det = FacturaService.delete(id);
		
		try {
			
			if(det) {
				return ResponseEntity.status(204).body("{ \"Succesful\": \"Correctly removed.\" }");
			}
			else {
				throw new StatusException("Bad request. Please verify the id or if exist a relation with another table", 400);
			}
			
		} catch (StatusException e) {
			
			return e.getResponseStatus();
			
		}
		
	}
}
