package com.utn.demo.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	private int id;
	private boolean envioDelivery;
	private String horaEstimada;
	private EstadoDTO estado;
	private UsuarioDTO usuario;
	private DomicilioDTO domicilio;
	private List<DetalleDTO> detalle = new ArrayList<>();
	private boolean eliminado;
}
