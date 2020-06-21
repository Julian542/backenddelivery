package com.utn.demo.entity;

import java.io.Serializable;
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
	
	private double montoDescuento;
	
	private Configuracion empresa; /* datos de la empresa */
	
	private double total;
	
	private FormaPago formaPago;
	
	@ManyToOne
	private Cliente facturaCliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DetalleFactura detalleFactura; /* composicion */
}
