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
import com.utn.demo.service.RecaudacionesService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
@RequestMapping(path = "api/v1/recaudaciones")
public class RecaudacionesController {

	private RecaudacionesService RecaudacionesService;

	public RecaudacionesController(RecaudacionesService RecaudacionesService) {
		this.RecaudacionesService = RecaudacionesService;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity getOne(@PathVariable boolean bool,@PathVariable long idUsuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(RecaudacionesService.getOne(bool,idUsuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/all")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(RecaudacionesService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
}
