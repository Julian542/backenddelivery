package com.utn.demo.entity;

import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta extends FormaPago {
	
	private int dni;
	private String nombreTitular;
	private int numeroTarjeta;
}
