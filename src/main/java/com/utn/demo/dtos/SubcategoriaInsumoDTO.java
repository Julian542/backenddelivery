package com.utn.demo.dtos;

import java.util.List;

public class SubcategoriaInsumoDTO {
	private long id;
	private String nombre;
	private List<InsumoDTO> insumo;
	private CategoriaInsumoDTO categoriaInsumoMaestra;
}
