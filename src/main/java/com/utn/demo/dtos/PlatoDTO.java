package com.utn.demo.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatoDTO extends ArticuloDTO {


	private String nombre;
	private float precioVenta;
	private float precioCosto; /* esto se calcula el precio de los insumos*/
	private int tiempoPreparacion;
	private String descripcion;
	private String imagen;
	private List<DetallePlatoDTO> receta;
	private CategoriaPlatoDTO categoriaPlato;
	private SubcategoriaPlatoDTO subcategoriaPlato;
}
