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
import com.utn.demo.dtos.FacturaDTO;
import com.utn.demo.service.FacturaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
@RequestMapping(path = "api/v1/factura")
public class FacturaController {

	private FacturaService facturaService;

	public FacturaController(FacturaService facturaService) {
		this.facturaService = facturaService;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity<Object> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(facturaService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(facturaService.getOne(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
	
	@GetMapping("/getFacturasMenosFacturados/")
	@Transactional
	public ResponseEntity<Object> getAllMenosFacturados() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(facturaService.getAllMenosFacturados());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/recaudaciones/{fechaDesde}/{fechaHasta}")
	@Transactional
	public ResponseEntity<Object> getRecaudaciones(@PathVariable String fechaDesde, @PathVariable String fechaHasta) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(facturaService.getRecaudaciones(fechaDesde, fechaHasta));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/platosPopulares/{fechaDesde}/{fechaHasta}")
	@Transactional
	public ResponseEntity<Object> getPlatosPopulares(@PathVariable String fechaDesde, @PathVariable String fechaHasta) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(facturaService.getPlatosPopulares(fechaDesde, fechaHasta));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<Object> post(@RequestBody FacturaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(facturaService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> put(@PathVariable int id, @RequestBody FacturaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(facturaService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		boolean borrado = facturaService.delete(id);
		if (borrado) {
			return ResponseEntity.status(HttpStatus.OK).body(borrado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("'message':'Error al eliminar'");
		}
	}
	
	@GetMapping("/getAllEnLocal/")
	@Transactional
	public ResponseEntity<Object> getAllEnLocal() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(facturaService.getAllEnLocal());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
}
