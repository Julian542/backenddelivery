package com.utn.demo.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {
	private long numeroFactura;
	private Date fecha;
	private String tipo;
	private double montoDescuento;
	private ConfiguracionDTO empresa;
	private double total;
	private PedidoDTO pedido;
	private ClienteDTO facturaCliente;
}
