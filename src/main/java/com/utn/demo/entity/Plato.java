package com.utn.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Where;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause = " eliminado = false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private float precioVenta;
	private float precioCosto;
	private int tiempoPreparacion;
	private String descripcion;
	private String imagen;
	private int cantidadVendida;
	@OneToOne
	@JoinColumn(name = "fk_categoria")
	private PlatoCategoria categoria;
	@Column(name = "eliminado")
	private boolean eliminado;
}
