package com.utn.demo.entity;

import java.time.LocalDate;
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
public class Detalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate fecha;
	private int cantidad;
	@OneToOne
	private Pedido pedido;
	@OneToOne
	@JoinColumn(name = "fk_plato")
	private Plato plato;
	@OneToOne
	@JoinColumn(name = "fk_insumo")
	private Insumo insumo;
	@Column(name = "eliminado")
	private boolean eliminado;
}
