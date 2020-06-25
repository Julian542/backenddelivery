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
import com.utn.demo.service.PlatosPorClienteService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
@RequestMapping(path = "api/v1/platosPorCliente")
public class PlatosPorClienteController {
	private PlatosPorClienteService PlatosPorClienteService;

	public PlatosPorClienteController(PlatosPorClienteService PlatosPorClienteService) {
		this.PlatosPorClienteService = PlatosPorClienteService;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity getOne(@PathVariable long id, @PathVariable int mes) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(PlatosPorClienteService.getOne(id, mes));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
}
