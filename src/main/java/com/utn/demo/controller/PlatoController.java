package com.utn.demo.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.service.PlatoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
@RequestMapping(path = "api/v1/plato")
public class PlatoController {

	private PlatoService platoService;

	public PlatoController(PlatoService InsumoService) {
		this.platoService = InsumoService;
	}

	@PostMapping("/uploadImg")
	@Transactional
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(platoService.uploadFile(file));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"message\": \"Error ocurrido en uploadFile\"}");
		}
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity<Object> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(platoService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/searchByCategory/{categoria}")
	@Transactional
	public ResponseEntity<Object> platosCategoria(@PathVariable String categoria) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(platoService.platosCategoria(categoria));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/verificarStock/{id}/{cantidad}")
	@Transactional
	public ResponseEntity verificarStock(@PathVariable int id, @PathVariable int cantidad) {
		try {

			return ResponseEntity.status(HttpStatus.OK).body(platoService.verificarStock(id, cantidad));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/platoPorCategoria/{id}")
	@Transactional
	public ResponseEntity<Object> getPorCategoria(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(platoService.getPlatoPorCategoria(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(platoService.getOne(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<Object> post(@RequestBody PlatoDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(platoService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> put(@PathVariable int id, @RequestBody PlatoDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(platoService.update(dto, id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		boolean borrado = platoService.delete(id);
		if (borrado) {
			return ResponseEntity.status(HttpStatus.OK).body(borrado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("'message':'Error al eliminar'");
		}
	}
}
