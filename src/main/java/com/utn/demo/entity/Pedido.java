package com.utn.demo.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Where(clause = "eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean envioDelivery;
	private int tiempoPreparacion;
	@OneToOne
	private Domicilio domicilio;
	@OneToOne(cascade = CascadeType.DETACH)
	private Estado estado;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;
	@Column(name = "eliminado")
	private boolean eliminado;
}
