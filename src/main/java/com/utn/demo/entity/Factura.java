package com.utn.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numeroFactura;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private String tipo;
	
	private double montoDescuento;
	
	private Configuracion empresa; /* datos de la empresa */
	
	private double total;
	
	/*private FormaPago formaPago;
	
	private Contado contado;
	
	private Tarjeta tarjeta;*/
	
	@OneToOne
	@JoinColumn(name = "fk_posee_pedido")
	private Pedido pedido;
	
	@ManyToOne
	private Cliente facturaCliente;
	
	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
	private List <DetalleFactura> detalleFactura; /* composicion */
}
