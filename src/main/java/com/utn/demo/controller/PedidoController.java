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
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.service.PedidoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
@RequestMapping(path = "api/v1/pedido")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity<Object> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}
	
	@GetMapping("/getPedidosMenosFacturados/")
	@Transactional
	public ResponseEntity<Object> getAllMenosFacturados() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getAllMenosFacturados());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/byUser/{id}")
	@Transactional
	public ResponseEntity<Object> getAllByUser(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getAllByUser(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/estado/{id}/{id2}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Object> getPedidoEstado(@PathVariable int id, @PathVariable int id2) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getPedidoEstado(id, id2));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<Object> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getOne(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/getPedidosPorUsuario/{id}/{fechaDesde}/{fechaHasta}")
	@Transactional
	public ResponseEntity<Object> getPedidosPorUsuario(@PathVariable int id, @PathVariable String fechaDesde,
			@PathVariable String fechaHasta) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(pedidoService.getPedidosPorUsuario(id, fechaDesde, fechaHasta));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@GetMapping("/getPedidos")
	@Transactional
	public ResponseEntity<Object> getPedidos() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getPedidos());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<Object> post(@RequestBody PedidoDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PutMapping("/updateTiempoRestante/{id}/{tiempoRestante}")
	@Transactional
	public ResponseEntity<String> updateTiempoRestante(@PathVariable("id") int id,
			@PathVariable("tiempoRestante") int tiempoRestante) {
		try {
			pedidoService.updateTiempoRestante(id, tiempoRestante);
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Actualizado\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PutMapping("/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Object> update(@RequestBody PedidoDTO t, @PathVariable int id) {
		PedidoDTO temp = pedidoService.update(t, id);
		try {
			if (temp.getId() != 0) {
				return ResponseEntity.status(201).body(temp);
			} else {
				throw new Exception("Error");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@PutMapping("changeStatus/{id}/{status}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Object> update(@RequestBody PedidoDTO t, @PathVariable("id") int id,
			@PathVariable("status") int status) {
		try {
			PedidoDTO temp = pedidoService.updateEstado(id, t, status);
			return ResponseEntity.status(201).body(temp);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Please try again later.\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		boolean borrado = pedidoService.delete(id);
		if (borrado) {
			return ResponseEntity.status(HttpStatus.OK).body(borrado);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("'message':'Error al eliminar'");
		}
	}
}