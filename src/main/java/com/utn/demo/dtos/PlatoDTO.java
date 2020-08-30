package com.utn.demo.dtos;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatoDTO {
	private int id;
	private String nombre;
	private float precioVenta;
	private float precioCosto;
	private int tiempoPreparacion;
	private String descripcion;
	private String imagen;
	private int cantidadVendida;
	private List<DetallePlatoDTO> detalle = new ArrayList<DetallePlatoDTO>();
	private PlatoCategoriaDTO categoria;
	private boolean eliminado;
}
