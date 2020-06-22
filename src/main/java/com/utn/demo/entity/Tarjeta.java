package com.utn.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "tarjeta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta extends FormaPago implements Serializable{
	
	private int dni;
	private String nombreTitular;
	private int numeroTarjeta;
}
