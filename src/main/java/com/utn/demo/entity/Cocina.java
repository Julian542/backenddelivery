package com.utn.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@PrimaryKeyJoinColumn(name = "cocinaId")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cocina implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double tiempo;
	
	@OneToMany(mappedBy = "cocina", cascade = CascadeType.ALL)
	private List <Pedido> listaPedido; /* composicion */
	
	
}
