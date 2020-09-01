package com.utn.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.FacturaDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.dtos.PlatosPopularesDTO;
import com.utn.demo.dtos.RecaudacionesDTO;
import com.utn.demo.dtos.UsuarioDTO;
import com.utn.demo.entity.Detalle;
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
				result.add(object);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public List<FacturaDTO> getAllMenosFacturados() {
		List<FacturaDTO> result = new ArrayList<>();
		try {
			for (Factura object2 : facturaRepository.findAllMod()) {
				if (object2.getPedido().getEstado().getId() != 6) {
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
					result.add(object);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public List<FacturaDTO> getAllEnLocal() {
		List<FacturaDTO> result = new ArrayList<>();
		try {
			for (Factura object2 : facturaRepository.findAllMod()) {
				if (object2.getPedido().getDomicilio().getId() == 99) {
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
					result.add(object);
				}
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
				for (Detalle detalle : detalleRepository.buscarPorPedido(factura.getPedido().getId())) {
					if (pedido.isEnvioDelivery() == true) {
						if (detalle.getPlato() == null) {
							Ganancias += ((detalle.getInsumo().getPrecioVenta()) * factura.getMontoDescuento() * detalle.getCantidad());
						} else {
							if (detalle.getInsumo() == null) {
								Ganancias += ((detalle.getPlato().getPrecioVenta()) * factura.getMontoDescuento() * detalle.getCantidad());
							} else {
								Ganancias += ((detalle.getInsumo().getPrecioVenta()
										+ detalle.getPlato().getPrecioVenta()) * factura.getMontoDescuento() * detalle.getCantidad());
							}
						}
					} else {
						if (detalle.getInsumo() == null) {
							Ganancias += (detalle.getPlato().getPrecioVenta() * detalle.getCantidad());
						} else {

							if (detalle.getPlato() == null) {
								Ganancias += (detalle.getInsumo().getPrecioVenta() * detalle.getCantidad());
							} else {
								Ganancias += (detalle.getInsumo().getPrecioVenta()
										+ detalle.getInsumo().getPrecioVenta() * detalle.getCantidad());
							}
						}
					}
					if (detalle.getInsumo() == null) {
						Gastos += (detalle.getPlato().getPrecioCosto() * detalle.getCantidad());
					} else {

						if (detalle.getPlato() == null) {
							Gastos += (detalle.getInsumo().getPrecioCompra() * detalle.getCantidad());
						} else {
							Gastos += (detalle.getInsumo().getPrecioCompra() + detalle.getPlato().getPrecioCosto() * detalle.getCantidad());
						}
					}

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
		List<String> Nombre = new ArrayList<String>();

		try {
			List<Factura> facturas = facturaRepository.getFacturasByDate(fechaDesde, fechaHasta);
			for (Factura factura : facturas) {
				if (factura.getPedido().getEstado().getId() == 6) {
					List<Detalle> detalles = detalleRepository.buscarPorPedidoFecha(factura.getPedido().getId(),
							fechaDesde, fechaHasta);
					boolean key = false;
					for (Detalle detalleInternal : detalles) {
						for (int plato : PlatoPopular) {
							if (detalleInternal.getPlato() != null) {
								if (detalleInternal.getPlato().getId() == plato) {
									key = true;
								}
							}
						}
						if (detalleInternal.getPlato() != null) {
							if (key == false) {
								PlatoPopular.add(detalleInternal.getPlato().getId());
								Nombre.add(detalleInternal.getPlato().getNombre());
								Cantidad.add(detalleInternal.getCantidad());
							} else {
								for (int i = 0; i < PlatoPopular.size(); i++) {
									if (PlatoPopular.get(i) == detalleInternal.getPlato().getId()) {
										Cantidad.set(i, Cantidad.get(i) + detalleInternal.getCantidad());
									}
								}
							}
						}

						key = false;

					}
				}
			}
			int i, j, aux, aux2;
			String aux3;
			for (i = 0; i < Cantidad.size() - 1; i++) {
				for (j = 0; j < Cantidad.size() - 1; j++) {

					if (Cantidad.get(j) < Cantidad.get(j + 1)) {
						aux = Cantidad.get(j);
						aux2 = PlatoPopular.get(j);
						aux3 = Nombre.get(j);
						Cantidad.set(j, Cantidad.get(j + 1));
						PlatoPopular.set(j, PlatoPopular.get(j + 1));
						Nombre.set(j, Nombre.get(j + 1));
						Cantidad.set(j + 1, aux);
						PlatoPopular.set(j + 1, aux2);
						Nombre.set(j + 1, aux3);
					}
				}
			}

			if (PlatoPopular.size() > 0) {
				platosPopulares.setId_Plato1(PlatoPopular.get(0));
				platosPopulares.setCantidad_Plato1(Cantidad.get(0));
				platosPopulares.setNombre_Plato1(Nombre.get(0));
			}
			if (PlatoPopular.size() > 1) {
				platosPopulares.setId_Plato2(PlatoPopular.get(1));
				platosPopulares.setCantidad_Plato2(Cantidad.get(1));
				platosPopulares.setNombre_Plato2(Nombre.get(1));

			}
			if (PlatoPopular.size() > 2) {
				platosPopulares.setId_Plato3(PlatoPopular.get(2));
				platosPopulares.setCantidad_Plato3(Cantidad.get(2));
				platosPopulares.setNombre_Plato3(Nombre.get(2));
			}
			if (PlatoPopular.size() > 3) {
				platosPopulares.setId_Plato4(PlatoPopular.get(3));
				platosPopulares.setCantidad_Plato4(Cantidad.get(3));
				platosPopulares.setNombre_Plato4(Nombre.get(3));
			}
			if (PlatoPopular.size() > 4) {
				platosPopulares.setId_Plato5(PlatoPopular.get(4));
				platosPopulares.setCantidad_Plato5(Cantidad.get(4));
				platosPopulares.setNombre_Plato5(Nombre.get(4));
			}
			if (PlatoPopular.size() > 5) {
				platosPopulares.setId_Plato6(PlatoPopular.get(5));
				platosPopulares.setCantidad_Plato6(Cantidad.get(5));
				platosPopulares.setNombre_Plato6(Nombre.get(5));
			}

		} catch (

		Exception e) {
			throw new Exception();
		}
		return platosPopulares;
	}
}
