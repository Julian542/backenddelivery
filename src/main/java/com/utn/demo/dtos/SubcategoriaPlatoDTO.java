package com.utn.demo.dtos;

import java.util.List;

public class SubcategoriaPlatoDTO {
	private long id;
	private String nombre;
	private List<PlatoDTO> platos;
	private CategoriaPlatoDTO categoriaMaestra;
}
