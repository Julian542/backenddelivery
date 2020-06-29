package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsumoDTO {

	private int id;

	private String nombre;
	private String descripcion;
	private double precioCompra;
	private double precioVenta;
	private double stockActual;
	private double stockMaximo;
	private double stockMinimo;
	private boolean esInsumo;
	private String imagen;

	private InsumoCategoriaDTO categoria;

	private UnidadMedidaDTO unidadMedida;
	private boolean eliminado;

}
