package com.utn.demo.entidades;

import javax.persistence.Entity;

@Entity
public class Tarjeta extends FormaPago {
	
	private int dni;
	private String nombreTitular;
	private int numeroTarjeta;
}
