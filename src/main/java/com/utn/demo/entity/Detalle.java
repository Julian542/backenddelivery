package com.utn.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause="eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detalle {

	@Column(name = "eliminado")
	private boolean eliminado;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_plato")
	private Plato plato;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_insumo")
	private Insumo insumo;

	@OneToOne
	private Pedido pedido;
}
