package com.utn.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsumoDTO extends ArticuloDTO{

	private String descripcion;
	private double precioCompra;
	private double precioVenta;
	private double stockActual;
	private double stockMaximo;
	private double stockMinimo;
	private boolean esIngrediente;
	private UnidadMedidaDTO unidadMedida;
	private String imagen;
	private CategoriaInsumoDTO categoriaInsumo;
	private SubcategoriaInsumoDTO subcategoriaInsumo;
}
