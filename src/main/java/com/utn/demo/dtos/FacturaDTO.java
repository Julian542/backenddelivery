package com.utn.demo.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO{
	private int id;
	private double subtotal;
	private double montoDescuento;
	private double total;
	private LocalDate fecha;
	private String tipoFactura;
	private String tipoPago;
	private String nroTarjeta;
	private int dniTitular;
	private UsuarioDTO usuario;
	private PedidoDTO pedido;
	private List<DetalleDTO> detalle = new ArrayList<>();
	private boolean eliminado;
}
