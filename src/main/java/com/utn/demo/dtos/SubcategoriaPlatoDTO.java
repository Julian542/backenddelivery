package com.utn.demo.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaPlatoDTO {
	private long id;
	private String nombre;
	private List<PlatoDTO> platos;
	private CategoriaPlatoDTO categoriaMaestra;
}
