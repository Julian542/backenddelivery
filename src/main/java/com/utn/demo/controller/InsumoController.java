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
	public ResponseEntity<Object> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/esInsumo/{esInsumo}")
	@Transactional
	public ResponseEntity<Object> getAllNoInsumos(@PathVariable boolean esInsumo) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getAllNoInsumos(esInsumo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getOne(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}

	}

	@GetMapping("/insumoporcategoria/{id}")
	@Transactional
	public ResponseEntity<Object> getAllporCategoria(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getAllporCategoria(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/buscarPorCategoriaNoInsumo/{id}")
	@Transactional
	public ResponseEntity<Object> buscarPorCategoriaNoInsumos(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.buscarPorCategoriaNoInsumo(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/stocks/{id}")
	@Transactional
	public ResponseEntity<Object> getStocks(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getStocks(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
	
	@GetMapping("/stocksSinCategoria/")
	@Transactional
	public ResponseEntity<Object> getStocksSinCategoria() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.getStocksSinCategoria());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<Object> post(@RequestBody InsumoDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(insumoService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> put(@PathVariable int id, @RequestBody InsumoDTO dto) {
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
		if (borrado) {
			return ResponseEntity.status(HttpStatus.OK).body(borrado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("'message':'Error al eliminar'");
		}
	}
}
