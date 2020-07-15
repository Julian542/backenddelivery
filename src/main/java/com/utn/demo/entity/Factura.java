package com.utn.demo.entity;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause="eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;

	private float montoDescuento;

	private float subtotal;
	
	private float total;

	private String tipoFactura;

	@OneToMany
	private List<Detalle> detalle = new ArrayList<>();

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "fk_pedido")
	private Pedido pedido;
	
	@Column(name = "eliminado")
	private boolean eliminado;

}