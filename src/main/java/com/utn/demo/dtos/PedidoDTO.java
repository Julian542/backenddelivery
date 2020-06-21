package com.utn.demo.dtos;

import java.util.List;


public class PedidoDTO {
	
	private long numeroPedido;
	private EstadoDTO estado;
	private ClienteDTO pedidoCliente;
	private FacturaDTO factura;
	List<DetallePedidoDTO> platos;
	private String tipoEnvio;	
	private String horaEstimada;
	private double subtotal;
}
