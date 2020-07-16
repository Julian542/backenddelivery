package com.utn.demo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Where;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Where(clause = "eliminado=false")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuracion_Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int cuit;
	private int numeroFiscal;
	private int telefono;
	private int cantidadCocineros;
	private String nombre;
	private String email;
	private String sociedad;
	private String paginaWeb;
	@Temporal(TemporalType.DATE)
	private Date horaApertura;
	@Temporal(TemporalType.DATE)
	private Date horaCierre;
	@Column(name = "eliminado")
	private boolean eliminado;
}