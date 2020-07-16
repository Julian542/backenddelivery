package com.utn.demo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	private int cantidadVendida;
	private int tiempoPreparacion;
	private double precioVenta;
	private double precioCosto;
	private String descripcion;
	private String imagen;
	private String nombre;
	@OneToOne
	@JoinColumn(name = "fk_categoria")
	private PlatoCategoria categoria;
	@OneToMany(cascade = CascadeType.ALL)
	private List<DetallePlato> detalle = new ArrayList<>();
	@Column(name = "eliminado")
	private boolean eliminado;
}
