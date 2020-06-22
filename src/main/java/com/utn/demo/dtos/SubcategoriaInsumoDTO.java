package com.utn.demo.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaInsumoDTO {
	private long id;
	private String nombre;
	private List<InsumoDTO> insumo;
	private CategoriaInsumoDTO categoriaInsumoMaestra;
}
