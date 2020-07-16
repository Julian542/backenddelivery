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
@Where(clause = "eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean envioDelivery;
	private String horaEstimada;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;
	@OneToOne(cascade = CascadeType.DETACH)
	private Estado estado;
	@OneToOne
	private Domicilio domicilio;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Detalle> detalle = new ArrayList<>();
	@Column(name = "eliminado")
	private boolean eliminado;
}
