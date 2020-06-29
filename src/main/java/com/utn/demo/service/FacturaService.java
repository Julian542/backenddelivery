package com.utn.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.DetalleDTO;
import com.utn.demo.dtos.FacturaDTO;
import com.utn.demo.dtos.RecaudacionesDTO;
import com.utn.demo.dtos.UsuarioDTO;
import com.utn.demo.entity.Detalle;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.Pedido;
import com.utn.demo.entity.Usuario;
import com.utn.demo.repository.FacturaRepository;

@Service
public class FacturaService {

	private FacturaRepository facturaRepository;

	public FacturaService(FacturaRepository facturaRepository) {
		this.facturaRepository = facturaRepository;
	}

	public List<FacturaDTO> getAll() {

		List<FacturaDTO> result = new ArrayList<>();

		for (Factura object2 : facturaRepository.findAll()) {
			FacturaDTO object = new FacturaDTO();
			object.setId(object2.getId());
			object.setTipoFactura(object2.getTipoFactura());
			object.setFecha(object2.getFecha());
			object.setSubtotal(object2.getSubtotal());
			object.setMontoDescuento(object2.getMontoDescuento());
			object.setTotal(object2.getTotal());
			object.setEliminado(object2.isEliminado());
			
			try {

				UsuarioDTO user = new UsuarioDTO();
				user.setId(object2.getUsuario().getId());
				object.setUsuario(user);

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

			try {
				List<DetalleDTO> detalle = new ArrayList<>();
				for (Detalle detalleInternal : object2.getDetalle()) {
					DetalleDTO detalleDTO = new DetalleDTO();
					detalleDTO.setId(detalleInternal.getId());
					detalleDTO.setCantidad(detalleInternal.getCantidad());
					detalle.add(detalleDTO);
				}
				object.setDetalle(detalle);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				List<DetalleDTO> detalle = new ArrayList<>();
				for (Detalle detalleInternal : object2.getDetalle()) {
					DetalleDTO detalleDTO = new DetalleDTO();
					detalleDTO.setId(detalleInternal.getId());
					detalle.add(detalleDTO);
				}
				object.setDetalle(detalle);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(object);
		}

		return result;

	}
	

	public FacturaDTO getOne(int id) {

		Optional<Factura> aOptional = facturaRepository.findById(id);
		FacturaDTO object = new FacturaDTO();

		try {

			Factura object2 = aOptional.get();
			object.setId(object2.getId());
			object.setTipoFactura(object2.getTipoFactura());
			object.setFecha(object2.getFecha());
			object.setSubtotal(object2.getSubtotal());
			object.setMontoDescuento(object2.getMontoDescuento());
			object.setTotal(object2.getTotal());
			object.setEliminado(object2.isEliminado());
			
			try {

				UsuarioDTO user = new UsuarioDTO();
				user.setId(object2.getUsuario().getId());
				object.setUsuario(user);

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

			try {
				List<DetalleDTO> detalle = new ArrayList<>();
				for (Detalle detalleInternal : object2.getDetalle()) {
					DetalleDTO detalleDTO = new DetalleDTO();
					detalleDTO.setId(detalleInternal.getId());
					detalleDTO.setCantidad(detalleInternal.getCantidad());
					detalle.add(detalleDTO);
				}
				object.setDetalle(detalle);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				List<DetalleDTO> detalle = new ArrayList<>();
				for (Detalle detalleInternal : object2.getDetalle()) {
					DetalleDTO detalleDTO = new DetalleDTO();
					detalleDTO.setId(detalleInternal.getId());
					detalle.add(detalleDTO);
				}
				object.setDetalle(detalle);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return object;

	}

	public FacturaDTO save(FacturaDTO facturaDTO) {

		Factura factura = new Factura();

		factura.setTipoFactura(facturaDTO.getTipoFactura());
		factura.setFecha(facturaDTO.getFecha());
		factura.setSubtotal(facturaDTO.getSubtotal());
		factura.setMontoDescuento(facturaDTO.getMontoDescuento());
		factura.setTotal(facturaDTO.getTotal());
		factura.setEliminado(facturaDTO.isEliminado());
		
		try {

			Usuario user = new Usuario();
			user.setId(facturaDTO.getUsuario().getId());
			factura.setUsuario(user);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		try {
			List<Detalle> detalle = new ArrayList<>();
			for (DetalleDTO detalleDTO : facturaDTO.getDetalle()) {
				Detalle detalleTemp = new Detalle();
				detalleTemp.setId(detalleDTO.getId());
				detalle.add(detalleTemp);
			}
			factura.setDetalle(detalle);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			List<Detalle> detalle = new ArrayList<>();
			for (DetalleDTO detalleDTO : facturaDTO.getDetalle()) {
				Detalle detalleTemp = new Detalle();
				detalleTemp.setId(detalleDTO.getId());
				detalle.add(detalleTemp);
			}
			factura.setDetalle(detalle);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		facturaRepository.save(factura);

		facturaDTO.setId(factura.getId());
		return facturaDTO;

	}

	public FacturaDTO update(int id, FacturaDTO facturaDTO) {

		Optional<Factura> optional = facturaRepository.findById(id);
		Factura factura = new Factura();

		try {

			factura = optional.get();

			factura.setTipoFactura(facturaDTO.getTipoFactura());
			factura.setFecha(facturaDTO.getFecha());
			factura.setSubtotal(facturaDTO.getSubtotal());
			factura.setMontoDescuento(facturaDTO.getMontoDescuento());
			factura.setTotal(facturaDTO.getTotal());
			factura.setEliminado(facturaDTO.isEliminado());
			
			try {

				Usuario user = new Usuario();
				user.setId(facturaDTO.getUsuario().getId());
				factura.setUsuario(user);

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

			try {
				List<Detalle> detalle = new ArrayList<>();
				for (DetalleDTO detalleDTO : facturaDTO.getDetalle()) {
					Detalle detalleTemp = new Detalle();
					detalleTemp.setId(detalleDTO.getId());
					detalle.add(detalleTemp);
				}
				factura.setDetalle(detalle);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				List<Detalle> detalle = new ArrayList<>();
				for (DetalleDTO detalleDTO : facturaDTO.getDetalle()) {
					Detalle detalleTemp = new Detalle();
					detalleTemp.setId(detalleDTO.getId());
					detalle.add(detalleTemp);
				}
				factura.setDetalle(detalle);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			facturaRepository.save(factura);

			facturaDTO.setId(factura.getId());

		} catch (Exception e) {

			System.out.println("Bad Request");
			facturaDTO.setId(0);

		}

		return facturaDTO;

	}

	public boolean delete(int id) {
		try {
			facturaRepository.deleteFacturaById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public RecaudacionesDTO getRecaudaciones(Date fechaDesde, Date fechaHasta) {
		RecaudacionesDTO recaudacionesDTO = new RecaudacionesDTO();
		try {
			List<Factura> facturas = facturaRepository.getFacturasByDate(fechaDesde, fechaHasta);

			double Total = new Double(0.0f);
			double Ganancias = new Double(0.0f);
			double Gastos = new Double(0.0f);

			recaudacionesDTO.setFechaDesde(fechaDesde);
			recaudacionesDTO.setFechaHasta(fechaHasta);

			for (Factura factura : facturas) {
				Pedido pedido = factura.getPedido();
				for (Detalle detalle : factura.getPedido().getDetalle()) {
					if(pedido.isEnvioDelivery() == true) {
						Ganancias += ((detalle.getInsumo().getPrecioVenta() + detalle.getPlato().getPrecioVenta()) * factura.getMontoDescuento());
					} else {
						Ganancias += (detalle.getInsumo().getPrecioVenta() + detalle.getPlato().getPrecioVenta());
					}
					Gastos += (detalle.getInsumo().getPrecioCompra() + detalle.getPlato().getPrecioCosto());
				}
			}
			
			Total = Ganancias - Gastos;
			recaudacionesDTO.setGanancias(Ganancias);
			recaudacionesDTO.setGastos(Gastos);
			recaudacionesDTO.setMontoNeto(Total);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return recaudacionesDTO;
	}
}
