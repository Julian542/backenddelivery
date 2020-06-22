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

import com.utn.demo.dtos.ConfiguracionDTO;
import com.utn.demo.exceptions.StatusException;
import com.utn.demo.service.ConfiguracionService;

@RestController
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.DELETE , RequestMethod.PUT, RequestMethod.POST})
@RequestMapping(path = "")
public class ConfiguracionController {

	private ConfiguracionService ConfiguracionService;

	public ConfiguracionController(ConfiguracionService ConfiguracionService){
		this.ConfiguracionService = ConfiguracionService;
	}

	@GetMapping("/")
	@CrossOrigin(origins = "*")
	public List<ConfiguracionDTO> getAll() {
		
		return ResponseEntity.status(200).body(ConfiguracionService.getAll()).getBody();
		
	}

	@GetMapping("/esInsumo/{esInsumo}")
	@CrossOrigin(origins = "*")
	public List<ConfiguracionDTO> getAllNoInsumos(@PathVariable boolean esInsumo) {
		
		return ResponseEntity.status(200).body(ConfiguracionService.getAllNoInsumos(esInsumo)).getBody();
		
	}

	@GetMapping("/{id}")
	@CrossOrigin(origins = "*")
	public ConfiguracionDTO getOne(@PathVariable int id) {
				
		return ResponseEntity.status(200).body(ConfiguracionService.getOne(id)).getBody();
		
	}

	@PostMapping("/")
	@CrossOrigin(origins = "*")
	public ResponseEntity save(@RequestBody ConfiguracionDTO t) {
		
		ConfiguracionDTO temp = ConfiguracionService.save(t);		
		
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
	public ResponseEntity update(@RequestBody ConfiguracionDTO t, @PathVariable int id) {
		
		ConfiguracionDTO temp = ConfiguracionService.update(t, id);
		
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
	
		boolean det = ConfiguracionService.delete(id);
		
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
