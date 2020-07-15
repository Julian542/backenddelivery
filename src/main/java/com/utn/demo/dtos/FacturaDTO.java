package com.utn.demo.dtos;

import java.io.Serializable;
import java.util.*;

import com.utn.demo.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO implements Serializable {

	private int id;

	private String tipoFactura;
	private Date fecha;
	private float subtotal;
	private float montoDescuento;
	private float total;

	private UsuarioDTO usuario;
	private List<DetalleDTO> detalle = new ArrayList<>();
	private Pedido pedido;
	private boolean eliminado;
}
