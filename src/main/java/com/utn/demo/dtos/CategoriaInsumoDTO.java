package com.utn.demo.dtos;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaInsumoDTO {
	private long id;
	private List<InsumoDTO> insumos;
	private List<SubcategoriaInsumoDTO> subcategorias_insumos;
}
