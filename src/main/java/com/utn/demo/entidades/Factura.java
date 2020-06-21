package com.utn.demo.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numeroFactura;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private double montoDescuento;
	
	private Configuracion empresa; /* datos de la empresa */
	
	private double total;
	
	private FormaPago formaPago;
	
	@ManyToOne
	private Cliente facturaCliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DetalleFactura detalleFactura; /* composicion */
}
