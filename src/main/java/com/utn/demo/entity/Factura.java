package com.utn.demo.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
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
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double montoDescuento;
	private double subtotal;
	private double total;
	private String tipoFactura;
	private String tipoPago;
	private String nroTarjeta;
	private int dniTitular;
	private LocalDate fecha;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;
	@OneToOne
	@JoinColumn(name = "fk_pedido")
	private Pedido pedido;
	@Column(name = "eliminado")
	private boolean eliminado;
}
