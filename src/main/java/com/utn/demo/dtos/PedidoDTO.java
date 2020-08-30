package com.utn.demo.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	private int id;
	private boolean envioDelivery;
	private float monto;
	private Date fecha;
	private int tiempoPreparacion;
	private EstadoDTO estado;
	private UsuarioDTO usuario;
	private DomicilioDTO domicilio;
	private boolean eliminado;

}
