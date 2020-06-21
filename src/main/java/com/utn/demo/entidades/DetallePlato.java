package com.utn.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetallePlato {
	/* esta clase representa un articulo de una receta, seria el detalle del maestro (plato)*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Insumo ingrediente;
	
	private int cantidad;
}
