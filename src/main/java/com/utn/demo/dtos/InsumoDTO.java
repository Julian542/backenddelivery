package com.utn.demo.dtos;

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
