package com.utn.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.RecaudacionesDTO;
import com.utn.demo.entity.DetallePedido;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.Recaudaciones;
import com.utn.demo.repository.RecaudacionesRepository;

@Service
public class RecaudacionesService {

	private RecaudacionesRepository RecaudacionesRepository;

	public RecaudacionesService(RecaudacionesRepository recaudacionesRepository) {
		RecaudacionesRepository = recaudacionesRepository;
	}

	public RecaudacionesDTO getOne(boolean mes, long idUsuario) throws Exception {
		RecaudacionesDTO dto = new RecaudacionesDTO();
		Recaudaciones entity = new Recaudaciones();
		Calendar now = new GregorianCalendar();
		Date fechaHoy = now.getTime();
		double sumaTotal = 0.0;
		double sumaTotalCosto = 0.0;
		if (mes) {
			now.add(Calendar.DATE, -30);
		} else {
			now.add(Calendar.DATE, -1);
		}
		Date fechaAnterior = now.getTime();

		try {
			List<Factura> facturas = RecaudacionesRepository.buscarFacturas(fechaAnterior, fechaHoy, idUsuario);
			for (Factura fac : facturas) {
				sumaTotal += fac.getTotal();
				List<DetallePedido> detallePedidos = fac.getPedido().getDetallePedido();
				for (DetallePedido detPedido : detallePedidos) {
					sumaTotalCosto += detPedido.getInsumo().getPrecioCompra() + detPedido.getPlato().getPrecioCosto();
				}
			}
			dto.setFecha(fechaHoy);
			dto.setGanancias(sumaTotal);
			dto.setGastos(sumaTotalCosto);
			dto.setMontoNeto(sumaTotal - sumaTotalCosto);
			entity.setFecha(dto.getFecha());
			entity.setGanancias(dto.getGanancias());
			entity.setGastos(dto.getGastos());
			entity.setMontoNeto(dto.getMontoNeto());
			RecaudacionesRepository.save(entity);
			dto.setId(entity.getId());
		} catch (Exception e) {
			throw new Exception();
		}
		return dto;
	}

	public List<RecaudacionesDTO> getAll() throws Exception {
		List<RecaudacionesDTO> recaudaciones = new ArrayList<>();
		try {
			for(Recaudaciones entity : RecaudacionesRepository.findAll()) {
				RecaudacionesDTO dto = new RecaudacionesDTO();
				dto.setId(entity.getId());
				dto.setFecha(entity.getFecha());
				dto.setGanancias(entity.getGanancias());
				dto.setGastos(entity.getGastos());
				dto.setMontoNeto(entity.getMontoNeto());
				recaudaciones.add(dto);
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return recaudaciones;
	}
}
