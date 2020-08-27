package com.utn.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.DetalleDTO;
import com.utn.demo.dtos.DetallePlatoDTO;
import com.utn.demo.dtos.FacturaDTO;
import com.utn.demo.dtos.InsumoCategoriaDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.dtos.PlatoCategoriaDTO;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.dtos.PlatosPopularesDTO;
import com.utn.demo.dtos.RecaudacionesDTO;
import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.dtos.UsuarioDTO;
import com.utn.demo.entity.Detalle;
import com.utn.demo.entity.DetallePlato;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.Pedido;
import com.utn.demo.entity.Usuario;
import com.utn.demo.repository.DetalleRepository;
import com.utn.demo.repository.FacturaRepository;
import com.utn.demo.repository.PedidoRepository;

@Service
public class FacturaService {

	private FacturaRepository facturaRepository;
	private DetalleRepository detalleRepository;

	public FacturaService(FacturaRepository facturaRepository, DetalleRepository detalleRepository,
			PedidoRepository pedidoRepository) {
		this.facturaRepository = facturaRepository;
		this.detalleRepository = detalleRepository;
	}

	@Transactional
	public List<FacturaDTO> getAll() {
		List<FacturaDTO> result = new ArrayList<>();
		try {
			for (Factura object2 : facturaRepository.findAllMod()) {
				FacturaDTO object = new FacturaDTO();
				object.setId(object2.getId());
				object.setTipoFactura(object2.getTipoFactura());
				object.setTipoPago(object2.getTipoPago());
				object.setNroTarjeta(object2.getNroTarjeta());
				object.setDniTitular(object2.getDniTitular());
				object.setFecha(object2.getFecha());
				object.setSubtotal(object2.getSubtotal());
				object.setMontoDescuento(object2.getMontoDescuento());
				object.setTotal(object2.getTotal());
				object.setEliminado(object2.isEliminado());
				PedidoDTO pedido = new PedidoDTO();
				pedido.setId(object2.getPedido().getId());
				object.setPedido(pedido);
				UsuarioDTO user = new UsuarioDTO();
				user.setId(object2.getUsuario().getId());
				object.setUsuario(user);
				List<DetalleDTO> detalle = new ArrayList<>();
				for (Detalle detalleInternal : object2.getDetalle()) {
					DetalleDTO detalleDTO = new DetalleDTO();
					detalleDTO.setId(detalleInternal.getId());
					detalleDTO.setCantidad(detalleInternal.getCantidad());
					detalle.add(detalleDTO);
				}
				object.setDetalle(detalle);
				result.add(object);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public FacturaDTO getOne(int id) {
		FacturaDTO object = new FacturaDTO();
		try {
			Factura object2 = facturaRepository.findByIdMod(id);
			object.setId(object2.getId());
			object.setTipoFactura(object2.getTipoFactura());
			object.setNroTarjeta(object2.getNroTarjeta());
			object.setDniTitular(object2.getDniTitular());
			object.setTipoPago(object2.getTipoPago());
			object.setFecha(object2.getFecha());
			object.setSubtotal(object2.getSubtotal());
			object.setMontoDescuento(object2.getMontoDescuento());
			object.setTotal(object2.getTotal());
			object.setEliminado(object2.isEliminado());
			try {
				PedidoDTO pedido = new PedidoDTO();
				pedido.setId(object2.getPedido().getId());
				object.setPedido(pedido);
				UsuarioDTO user = new UsuarioDTO();
				user.setId(object2.getUsuario().getId());
				object.setUsuario(user);
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
		} catch (Exception e) {
			System.out.println("No existe el id");
		}
		return object;
	}

	@Transactional
	public FacturaDTO save(FacturaDTO facturaDTO) {
		Factura factura = new Factura();
		try {
			factura.setTipoFactura(facturaDTO.getTipoFactura());
			factura.setTipoPago(facturaDTO.getTipoPago());
			factura.setFecha(facturaDTO.getFecha());
			factura.setSubtotal(facturaDTO.getSubtotal());
			factura.setMontoDescuento(facturaDTO.getMontoDescuento());
			factura.setTotal(facturaDTO.getTotal());
			factura.setNroTarjeta(facturaDTO.getNroTarjeta());
			factura.setDniTitular(facturaDTO.getDniTitular());
			factura.setEliminado(facturaDTO.isEliminado());
			Pedido pedido = new Pedido();
			pedido.setId(facturaDTO.getPedido().getId());
			factura.setPedido(pedido);
			Usuario user = new Usuario();
			user.setId(facturaDTO.getUsuario().getId());
			factura.setUsuario(user);
			List<Detalle> detalle = new ArrayList<>();
			for (DetalleDTO detalleDTO : facturaDTO.getDetalle()) {
				Detalle detalleTemp = new Detalle();
				detalleTemp.setId(detalleDTO.getId());
				detalle.add(detalleTemp);
			}
			factura.setDetalle(detalle);
			facturaRepository.save(factura);
			facturaDTO.setId(factura.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return facturaDTO;
	}

	@Transactional
	public FacturaDTO update(int id, FacturaDTO facturaDTO) {
		Optional<Factura> optional = facturaRepository.findById(id);
		Factura factura = new Factura();
		try {
			factura = optional.get();
			factura.setTipoFactura(facturaDTO.getTipoFactura());
			factura.setTipoPago(facturaDTO.getTipoPago());
			factura.setNroTarjeta(facturaDTO.getNroTarjeta());
			factura.setDniTitular(facturaDTO.getDniTitular());
			factura.setFecha(facturaDTO.getFecha());
			factura.setSubtotal(facturaDTO.getSubtotal());
			factura.setMontoDescuento(facturaDTO.getMontoDescuento());
			factura.setTotal(facturaDTO.getTotal());
			factura.setEliminado(facturaDTO.isEliminado());
			try {
				Pedido pedido = new Pedido();
				pedido.setId(facturaDTO.getPedido().getId());
				factura.setPedido(pedido);
				Usuario user = new Usuario();
				user.setId(facturaDTO.getUsuario().getId());
				factura.setUsuario(user);
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

	@Transactional
	public boolean delete(int id) {
		try {
			facturaRepository.deleteFacturaById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public RecaudacionesDTO getRecaudaciones(String fechaDesde, String fechaHasta) {
		RecaudacionesDTO recaudacionesDTO = new RecaudacionesDTO();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<Factura> facturas = facturaRepository.getFacturasByDate(fechaDesde, fechaHasta);
			double Total = new Double(0.0f);
			double Ganancias = new Double(0.0f);
			double Gastos = new Double(0.0f);
			Date fecha = formato.parse(fechaDesde);
			recaudacionesDTO.setFechaDesde(fecha);
			fecha = formato.parse(fechaHasta);
			recaudacionesDTO.setFechaHasta(fecha);
			for (Factura factura : facturas) {
				Pedido pedido = factura.getPedido();
				for (Detalle detalle : factura.getPedido().getDetalle()) {
					if (pedido.isEnvioDelivery() == true) {
						Ganancias += ((detalle.getInsumo().getPrecioVenta() + detalle.getPlato().getPrecioVenta())
								* factura.getMontoDescuento());
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

	@Transactional
	public PlatosPopularesDTO getPlatosPopulares(String fechaDesde, String fechaHasta) throws Exception {

		PlatosPopularesDTO platosPopulares = new PlatosPopularesDTO();
		List<Integer> PlatoPopular = new ArrayList<Integer>();
		List<Integer> Cantidad = new ArrayList<Integer>();
		List<PlatoDTO> finalPlatos = new ArrayList<PlatoDTO>();

		try {
			List<Factura> facturas = facturaRepository.getFacturasByDate(fechaDesde, fechaHasta);
			for (Factura factura : facturas) {
				List<Detalle> detalles = detalleRepository.buscarPorPedidoFecha(factura.getPedido().getId(),fechaDesde, fechaHasta);
				for (Detalle detalleInternal : detalles) {
					boolean key = false;
					int i;
					if (PlatoPopular.size() > 0 && detalleInternal.getPlato() != null) {

						for (i = 0; i < PlatoPopular.size(); i++) {
							if (PlatoPopular.get(i) == detalleInternal.getPlato().getId()) {

								Cantidad.add(i, Cantidad.get(i) + 1);

								key = true;
							}
						}
					}
					if (key == false && detalleInternal.getPlato() != null) {
						PlatoPopular.add(detalleInternal.getPlato().getId());
						Cantidad.add(1);
					}
					key = false;
				}
			}
			
			int i, j, aux, aux2;
			for (i = 0; i < Cantidad.size(); i++) {
				for (j = 0; j < Cantidad.size() - 1; j++) {
					if (j + 1 >= Cantidad.size()) {
						if (Cantidad.get(j + 1) < Cantidad.get(j)) {
							aux = Cantidad.get(j + 1);
							aux2 = PlatoPopular.get(j + 1);
							Cantidad.set(j + 1, Cantidad.get(j));
							PlatoPopular.set(j + 1, PlatoPopular.get(j));
							Cantidad.set(j, aux);
							PlatoPopular.set(j, aux2);
						}
					}
				}
			}
			
			if (PlatoPopular.size() > 0) {
				platosPopulares.setId_Plato1(PlatoPopular.get(0));
				platosPopulares.setCantidad_Plato1(Cantidad.get(0));
			}
			if (PlatoPopular.size() > 1) {
				platosPopulares.setId_Plato2(PlatoPopular.get(1));
				platosPopulares.setCantidad_Plato2(Cantidad.get(1));
			}
			if (PlatoPopular.size() > 2) {
				platosPopulares.setId_Plato3(PlatoPopular.get(2));
				platosPopulares.setCantidad_Plato3(Cantidad.get(2));
			}
			if (PlatoPopular.size() > 3) {
				platosPopulares.setId_Plato4(PlatoPopular.get(3));
				platosPopulares.setCantidad_Plato4(Cantidad.get(3));
			}
			if (PlatoPopular.size() > 4) {
				platosPopulares.setId_Plato5(PlatoPopular.get(4));
				platosPopulares.setCantidad_Plato5(Cantidad.get(4));
			}
			if (PlatoPopular.size() > 5) {
				platosPopulares.setId_Plato6(PlatoPopular.get(5));
				platosPopulares.setCantidad_Plato6(Cantidad.get(5));
			}

		} catch (

		Exception e) {
			throw new Exception();
		}
		return platosPopulares;
	}
}
