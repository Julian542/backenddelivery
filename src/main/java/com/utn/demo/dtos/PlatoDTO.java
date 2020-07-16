package com.utn.demo.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatoDTO {
	private int id;
	private int tiempoPreparacion;
	private int cantidadVendida;
	private double precioVenta;
	private double precioCosto;
	private String nombre;
	private String descripcion;
	private String imagen;
	private PlatoCategoriaDTO categoria;
	private List<DetallePlatoDTO> detalle;
	private boolean eliminado;
}
