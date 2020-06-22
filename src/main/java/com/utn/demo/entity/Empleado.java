package com.utn.demo.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado extends Usuario implements Serializable{
	
	
	private String rol;
}
