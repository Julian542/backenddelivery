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
@Where(clause = "eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double precioCompra;
	private double precioVenta;
	private double stockActual;
	private double stockMaximo;
	private double stockMinimo;
	private boolean esInsumo;
	private String nombre;
	private String descripcion;
	private String imagen;
	@OneToOne
	@JoinColumn(name = "fk_categoria")
	private InsumoCategoria categoria;
	@OneToOne
	@JoinColumn(name = "fk_unidad_medida")
	private UnidadMedida unidadMedida;
	@Column(name = "eliminado")
	private boolean eliminado;
}
