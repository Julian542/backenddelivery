package com.utn.demo.dtos;

import java.util.List;

public class CategoriaPlatoDTO {
	private long id;
	private String nombre;
	private List<PlatoDTO> platos;
	private List<SubcategoriaPlatoDTO> subcategorias_plato;
}
