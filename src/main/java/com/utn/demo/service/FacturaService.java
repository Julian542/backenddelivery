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
import com.utn.demo.dtos.RecaudacionesDTO;
import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.dtos.UsuarioDTO;
import com.utn.demo.entity.Detalle;
import com.utn.demo.entity.DetallePlato;
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

	@Transactional
	public List<FacturaDTO> getAll() {
		List<FacturaDTO> result = new ArrayList<>();
		try {
			for (Factura object2 : facturaRepository.findAll()) {
				FacturaDTO object = new FacturaDTO();
				object.setId(object2.getId());
				object.setTipoFactura(object2.getTipoFactura());
				object.setTipoPago(object2.getTipoPago());
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
		Optional<Factura> aOptional = facturaRepository.findById(id);
		FacturaDTO object = new FacturaDTO();
		try {
			Factura object2 = aOptional.get();
			object.setId(object2.getId());
			object.setTipoFactura(object2.getTipoFactura());
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
	public List<PlatoDTO> getPlatosPopulares(String fechaDesde, String fechaHasta) throws Exception {
		List<PlatoDTO> platoDTOs = new ArrayList<PlatoDTO>();
		try {
			System.out.println(fechaDesde);
			System.out.println(fechaHasta);
			List<Factura> facturas = facturaRepository.getFacturasByDate(fechaDesde, fechaHasta);
			for (Factura factura : facturas) {
				for (Detalle detalleInternal : factura.getDetalle()) {
					PlatoDTO platoDTO = new PlatoDTO();
					platoDTO.setId(detalleInternal.getPlato().getId());
					if(platoDTO.getId() != 6) {
						platoDTO.setCantidadVendida(detalleInternal.getPlato().getCantidadVendida());
						platoDTO.setDescripcion(detalleInternal.getPlato().getDescripcion());
						platoDTO.setImagen(detalleInternal.getPlato().getImagen());
						platoDTO.setNombre(detalleInternal.getPlato().getNombre());
						platoDTO.setPrecioCosto(detalleInternal.getPlato().getPrecioCosto());
						platoDTO.setPrecioVenta(detalleInternal.getPlato().getPrecioVenta());
						platoDTO.setTiempoPreparacion(detalleInternal.getPlato().getTiempoPreparacion());
						platoDTO.setEliminado(detalleInternal.getPlato().isEliminado());
						List<DetallePlatoDTO> platoDetalle = new ArrayList<>();
						for (DetallePlato platoDetalleInternal : detalleInternal.getPlato().getDetalle()) {
							DetallePlatoDTO platoDetalleDTO = new DetallePlatoDTO();
							platoDetalleDTO.setId(platoDetalleInternal.getId());
							platoDetalleDTO.setCantidad(platoDetalleInternal.getCantidad());
							InsumoDTO insumo = new InsumoDTO();
							insumo.setDescripcion(platoDetalleInternal.getInsumo().getDescripcion());
							insumo.setEsInsumo(platoDetalleInternal.getInsumo().isEsInsumo());
							insumo.setId(platoDetalleInternal.getInsumo().getId());
							insumo.setStockActual(platoDetalleInternal.getInsumo().getStockActual());
							insumo.setStockMaximo(platoDetalleInternal.getInsumo().getStockMaximo());
							insumo.setStockMinimo(platoDetalleInternal.getInsumo().getStockMinimo());
							insumo.setNombre(platoDetalleInternal.getInsumo().getNombre());
							insumo.setPrecioCompra(platoDetalleInternal.getInsumo().getPrecioCompra());
							insumo.setPrecioVenta(platoDetalleInternal.getInsumo().getPrecioVenta());
							insumo.setEliminado(platoDetalleInternal.getInsumo().isEliminado());
							InsumoCategoriaDTO insumoCategoria = new InsumoCategoriaDTO();
							insumoCategoria.setId(platoDetalleInternal.getInsumo().getCategoria().getId());
							insumoCategoria.setNombre(platoDetalleInternal.getInsumo().getCategoria().getNombre());
							insumoCategoria.setDescripcion(platoDetalleInternal.getInsumo().getCategoria().getDescripcion());
							insumoCategoria.setEliminado(platoDetalleInternal.getInsumo().getCategoria().isEliminado());
							insumo.setCategoria(insumoCategoria);
							UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
							unidadMedida.setId(platoDetalleInternal.getInsumo().getUnidadMedida().getId());
							unidadMedida.setNombre(platoDetalleInternal.getInsumo().getUnidadMedida().getNombre());
							unidadMedida.setAbreviatura(platoDetalleInternal.getInsumo().getUnidadMedida().getAbreviatura());
							unidadMedida.setEliminado(platoDetalleInternal.getInsumo().getUnidadMedida().isEliminado());
							insumo.setUnidadMedida(unidadMedida);
							platoDetalleDTO.setIngrediente(insumo);
							platoDetalle.add(platoDetalleDTO);
						}
						platoDTO.setDetalle(platoDetalle);
						PlatoCategoriaDTO platoCategoria = new PlatoCategoriaDTO();
						platoCategoria.setId(detalleInternal.getPlato().getCategoria().getId());
						platoCategoria.setNombre(detalleInternal.getPlato().getCategoria().getNombre());
						platoCategoria.setDescripcion(detalleInternal.getPlato().getCategoria().getDescripcion());
						platoCategoria.setEliminado(detalleInternal.getPlato().getCategoria().isEliminado());
						platoDTO.setCategoria(platoCategoria);
						platoDTOs.add(platoDTO);
					}
				}
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return platoDTOs;
	}
}
