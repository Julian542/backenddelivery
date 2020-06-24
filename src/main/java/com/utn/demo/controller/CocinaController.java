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

import com.utn.demo.dtos.CocinaDTO;
import com.utn.demo.service.CocinaService;

@RestController
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.DELETE , RequestMethod.PUT, RequestMethod.POST})
@RequestMapping(path = "")
public class CocinaController {

	private CocinaService cocinaService;

	public CocinaController(CocinaService cocinaService){
		this.cocinaService = cocinaService;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cocinaService.findAll());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable long id){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cocinaService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
		}
		
	}
	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody CocinaDTO dto){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cocinaService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
			
		}
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable long id, @RequestBody CocinaDTO dto){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cocinaService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
		}
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity post(@PathVariable long id){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cocinaService.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
}
