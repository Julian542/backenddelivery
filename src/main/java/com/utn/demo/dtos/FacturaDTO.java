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
	private double montoDescuento;
	private ConfiguracionDTO empresa;
	private double total;
	private ContadoDTO contado;
	private TarjetaDTO tarjeta;
	private ClienteDTO facturaCliente;
	private DetalleFacturaDTO detalleFactura;
}
