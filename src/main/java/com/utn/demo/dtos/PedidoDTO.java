package com.utn.demo.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	
	private long numeroPedido;
	private EstadoDTO estado;
	private ClienteDTO pedidoCliente;
	private FacturaDTO factura;
	List<DetallePedidoDTO> detallePedido;
	private String tipoEnvio;	
	private String horaEstimada;
	private double subtotal;
}
