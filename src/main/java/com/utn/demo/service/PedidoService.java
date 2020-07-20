package com.utn.demo.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.utn.demo.dtos.DetalleDTO;
import com.utn.demo.dtos.DetallePlatoDTO;
import com.utn.demo.dtos.DomicilioDTO;
import com.utn.demo.dtos.EstadoDTO;
import com.utn.demo.dtos.InsumoCategoriaDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.LocalidadDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.dtos.UsuarioDTO;
import com.utn.demo.entity.Detalle;
import com.utn.demo.entity.DetallePlato;
import com.utn.demo.entity.Domicilio;
import com.utn.demo.entity.Estado;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.Pedido;
import com.utn.demo.entity.Plato;
import com.utn.demo.entity.Usuario;
import com.utn.demo.repository.InsumoRepository;
import com.utn.demo.repository.PedidoRepository;
import com.utn.demo.repository.PlatoRepository;

@Service
public class PedidoService {

	private PedidoRepository pedidoRepository;
	private InsumoRepository insumoRepository;
	private PlatoRepository platoRepository;

	public PedidoService(PedidoRepository pedidoRepository, InsumoRepository insumoRepository,
			PlatoRepository platoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.insumoRepository = insumoRepository;
		this.platoRepository = platoRepository;
	}

	@Transactional
	public List<PedidoDTO> getAll() {
		List<PedidoDTO> result = new ArrayList<>();
		try {
			for (Pedido dto2 : pedidoRepository.findAll()) {
				PedidoDTO dto = new PedidoDTO();
				dto.setId(dto2.getId());
				dto.setHoraEstimada(dto2.getHoraEstimada());
				dto.setEnvioDelivery(dto2.isEnvioDelivery());
				UsuarioDTO user = new UsuarioDTO();
				user.setId(dto2.getUsuario().getId());
				dto.setUsuario(user);
				DomicilioDTO domicilioDTO = new DomicilioDTO();
				domicilioDTO.setId(dto2.getDomicilio().getId());
				dto.setDomicilio(domicilioDTO);
				EstadoDTO estado = new EstadoDTO();
				estado.setId(dto2.getEstado().getId());
				estado.setNombre(dto2.getEstado().getNombre());
				dto.setEstado(estado);
				List<DetalleDTO> detalle = new ArrayList<>();
				for (Detalle detalleInternal : dto2.getDetalle()) {
					DetalleDTO detalleDTO = new DetalleDTO();
					detalleDTO.setId(detalleInternal.getId());
					detalleDTO.setCantidad(detalleInternal.getCantidad());
					if (detalleInternal.getInsumo() != null) {
						InsumoDTO insumo = new InsumoDTO();
						insumo.setDescripcion(detalleInternal.getInsumo().getDescripcion());
						insumo.setEsInsumo(detalleInternal.getInsumo().isEsInsumo());
						insumo.setId(detalleInternal.getInsumo().getId());
						insumo.setStockActual(detalleInternal.getInsumo().getStockActual());
						insumo.setStockMaximo(detalleInternal.getInsumo().getStockMaximo());
						insumo.setStockMinimo(detalleInternal.getInsumo().getStockMinimo());
						insumo.setNombre(detalleInternal.getInsumo().getNombre());
						insumo.setPrecioCompra(detalleInternal.getInsumo().getPrecioCompra());
						insumo.setPrecioVenta(detalleInternal.getInsumo().getPrecioVenta());
						UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
						unidadMedida.setId(detalleInternal.getInsumo().getUnidadMedida().getId());
						unidadMedida.setNombre(detalleInternal.getInsumo().getUnidadMedida().getNombre());
						unidadMedida.setAbreviatura(detalleInternal.getInsumo().getUnidadMedida().getAbreviatura());
						insumo.setUnidadMedida(unidadMedida);
						detalleDTO.setInsumo(insumo);
					}
					if (detalleInternal.getPlato() != null) {
						PlatoDTO plato = new PlatoDTO();
						plato.setId(detalleInternal.getPlato().getId());
						plato.setNombre(detalleInternal.getPlato().getNombre());
						plato.setTiempoPreparacion(detalleInternal.getPlato().getTiempoPreparacion());
						List<DetallePlatoDTO> detallesPlato = new ArrayList<>();
						for (DetallePlato platoDetalleInternal : detalleInternal.getPlato().getDetalle()) {
							DetallePlatoDTO temp = new DetallePlatoDTO();
							temp.setId(platoDetalleInternal.getId());
							temp.setCantidad(platoDetalleInternal.getCantidad());
							InsumoDTO insumoDetalle = new InsumoDTO();
							insumoDetalle.setDescripcion(platoDetalleInternal.getInsumo().getDescripcion());
							insumoDetalle.setEsInsumo(platoDetalleInternal.getInsumo().isEsInsumo());
							insumoDetalle.setId(platoDetalleInternal.getInsumo().getId());
							insumoDetalle.setStockActual(platoDetalleInternal.getInsumo().getStockActual());
							insumoDetalle.setStockMaximo(platoDetalleInternal.getInsumo().getStockMaximo());
							insumoDetalle.setStockMinimo(platoDetalleInternal.getInsumo().getStockMinimo());
							insumoDetalle.setNombre(platoDetalleInternal.getInsumo().getNombre());
							insumoDetalle.setPrecioCompra(platoDetalleInternal.getInsumo().getPrecioCompra());
							insumoDetalle.setPrecioVenta(platoDetalleInternal.getInsumo().getPrecioVenta());
							InsumoCategoriaDTO insumoCategoria = new InsumoCategoriaDTO();
							insumoCategoria.setId(platoDetalleInternal.getInsumo().getCategoria().getId());
							insumoCategoria.setNombre(platoDetalleInternal.getInsumo().getCategoria().getNombre());
							insumoCategoria
									.setDescripcion(platoDetalleInternal.getInsumo().getCategoria().getDescripcion());
							insumoDetalle.setCategoria(insumoCategoria);
							UnidadMedidaDTO unidadMedidaDetalle = new UnidadMedidaDTO();
							unidadMedidaDetalle.setId(platoDetalleInternal.getInsumo().getUnidadMedida().getId());
							unidadMedidaDetalle
									.setNombre(platoDetalleInternal.getInsumo().getUnidadMedida().getNombre());
							unidadMedidaDetalle.setAbreviatura(
									platoDetalleInternal.getInsumo().getUnidadMedida().getAbreviatura());
							insumoDetalle.setUnidadMedida(unidadMedidaDetalle);
							temp.setIngrediente(insumoDetalle);
							detallesPlato.add(temp);
						}
						plato.setDetalle(detallesPlato);
						detalleDTO.setPlato(plato);
					}
					detalle.add(detalleDTO);
				}
				dto.setDetalle(detalle);
				result.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public List<PedidoDTO> getAllByUser(int id) {
		List<PedidoDTO> result = new ArrayList<>();
		try {
			for (Pedido dto2 : pedidoRepository.getAllByUser(id)) {
				PedidoDTO dto = new PedidoDTO();
				dto.setId(dto2.getId());
				dto.setHoraEstimada(dto2.getHoraEstimada());
				dto.setEnvioDelivery(dto2.isEnvioDelivery());
				dto.setEliminado(dto2.isEliminado());
				UsuarioDTO user = new UsuarioDTO();
				user.setId(dto2.getUsuario().getId());
				dto.setUsuario(user);
				EstadoDTO estado = new EstadoDTO();
				estado.setId(dto2.getEstado().getId());
				estado.setNombre(dto2.getEstado().getNombre());
				estado.setEliminado(dto2.getEstado().isEliminado());
				dto.setEstado(estado);
				DomicilioDTO domicilioDTO = new DomicilioDTO();
				domicilioDTO.setId(dto2.getDomicilio().getId());
				dto.setDomicilio(domicilioDTO);
				List<DetalleDTO> detalle = new ArrayList<>();
				for (Detalle detalleInternal : dto2.getDetalle()) {
					DetalleDTO detalleDTO = new DetalleDTO();
					detalleDTO.setId(detalleInternal.getId());
					detalleDTO.setCantidad(detalleInternal.getCantidad());
					detalleDTO.setEliminado(detalleInternal.isEliminado());
					if (detalleInternal.getInsumo() != null) {
						InsumoDTO insumo = new InsumoDTO();
						insumo.setDescripcion(detalleInternal.getInsumo().getDescripcion());
						insumo.setEsInsumo(detalleInternal.getInsumo().isEsInsumo());
						insumo.setId(detalleInternal.getInsumo().getId());
						insumo.setStockActual(detalleInternal.getInsumo().getStockActual());
						insumo.setStockMaximo(detalleInternal.getInsumo().getStockMaximo());
						insumo.setStockMinimo(detalleInternal.getInsumo().getStockMinimo());
						insumo.setNombre(detalleInternal.getInsumo().getNombre());
						insumo.setPrecioCompra(detalleInternal.getInsumo().getPrecioCompra());
						insumo.setPrecioVenta(detalleInternal.getInsumo().getPrecioVenta());
						insumo.setEliminado(detalleInternal.getInsumo().isEliminado());
						UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
						unidadMedida.setId(detalleInternal.getInsumo().getUnidadMedida().getId());
						unidadMedida.setNombre(detalleInternal.getInsumo().getUnidadMedida().getNombre());
						unidadMedida.setAbreviatura(detalleInternal.getInsumo().getUnidadMedida().getAbreviatura());
						unidadMedida.setEliminado(detalleInternal.getInsumo().getUnidadMedida().isEliminado());
						insumo.setUnidadMedida(unidadMedida);
						detalleDTO.setInsumo(insumo);
					}
					if (detalleInternal.getPlato() != null) {
						PlatoDTO plato = new PlatoDTO();
						plato.setId(detalleInternal.getPlato().getId());
						plato.setNombre(detalleInternal.getPlato().getNombre());
						plato.setTiempoPreparacion(detalleInternal.getPlato().getTiempoPreparacion());
						plato.setEliminado(detalleInternal.getPlato().isEliminado());
						List<DetallePlatoDTO> detallesPlato = new ArrayList<>();
						for (DetallePlato platoDetalleInternal : detalleInternal.getPlato().getDetalle()) {
							DetallePlatoDTO temp = new DetallePlatoDTO();
							temp.setId(platoDetalleInternal.getId());
							temp.setCantidad(platoDetalleInternal.getCantidad());
							InsumoDTO insumoDetalle = new InsumoDTO();
							insumoDetalle.setDescripcion(platoDetalleInternal.getInsumo().getDescripcion());
							insumoDetalle.setEsInsumo(platoDetalleInternal.getInsumo().isEsInsumo());
							insumoDetalle.setId(platoDetalleInternal.getInsumo().getId());
							insumoDetalle.setStockActual(platoDetalleInternal.getInsumo().getStockActual());
							insumoDetalle.setStockMaximo(platoDetalleInternal.getInsumo().getStockMaximo());
							insumoDetalle.setStockMinimo(platoDetalleInternal.getInsumo().getStockMinimo());
							insumoDetalle.setNombre(platoDetalleInternal.getInsumo().getNombre());
							insumoDetalle.setPrecioCompra(platoDetalleInternal.getInsumo().getPrecioCompra());
							insumoDetalle.setPrecioVenta(platoDetalleInternal.getInsumo().getPrecioVenta());
							insumoDetalle.setEliminado(platoDetalleInternal.getInsumo().isEliminado());
							InsumoCategoriaDTO insumoCategoria = new InsumoCategoriaDTO();
							insumoCategoria.setId(platoDetalleInternal.getInsumo().getCategoria().getId());
							insumoCategoria.setNombre(platoDetalleInternal.getInsumo().getCategoria().getNombre());
							insumoCategoria
									.setDescripcion(platoDetalleInternal.getInsumo().getCategoria().getDescripcion());
							insumoCategoria.setEliminado(platoDetalleInternal.getInsumo().getCategoria().isEliminado());
							insumoDetalle.setCategoria(insumoCategoria);
							UnidadMedidaDTO unidadMedidaDetalle = new UnidadMedidaDTO();
							unidadMedidaDetalle.setId(platoDetalleInternal.getInsumo().getUnidadMedida().getId());
							unidadMedidaDetalle
									.setNombre(platoDetalleInternal.getInsumo().getUnidadMedida().getNombre());
							unidadMedidaDetalle.setAbreviatura(
									platoDetalleInternal.getInsumo().getUnidadMedida().getAbreviatura());
							unidadMedidaDetalle
									.setEliminado(platoDetalleInternal.getInsumo().getUnidadMedida().isEliminado());
							insumoDetalle.setUnidadMedida(unidadMedidaDetalle);
							temp.setIngrediente(insumoDetalle);
							detallesPlato.add(temp);
						}
						plato.setDetalle(detallesPlato);
						detalleDTO.setPlato(plato);
					}
					detalle.add(detalleDTO);
				}
				dto.setDetalle(detalle);
				result.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public PedidoDTO getOne(int id) {
		Optional<Pedido> aOptional = pedidoRepository.findById(id);
		PedidoDTO dto = new PedidoDTO();
		try {
			Pedido dto2 = aOptional.get();
			dto.setId(dto2.getId());
			dto.setHoraEstimada(dto2.getHoraEstimada());
			dto.setEnvioDelivery(dto2.isEnvioDelivery());
			try {
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setId(dto2.getUsuario().getId());
				usuarioDTO.setNombre(dto2.getUsuario().getNombre());
				usuarioDTO.setApellido(dto2.getUsuario().getApellido());
				dto.setUsuario(usuarioDTO);
				EstadoDTO estadoDTO = new EstadoDTO();
				estadoDTO.setId(dto2.getEstado().getId());
				estadoDTO.setNombre(dto2.getEstado().getNombre());
				estadoDTO.setNombre(dto2.getEstado().getNombre());
				dto.setEstado(estadoDTO);
				DomicilioDTO domicilioDTO = new DomicilioDTO();
				domicilioDTO.setId(dto2.getDomicilio().getId());
				domicilioDTO.setCalle(dto2.getDomicilio().getCalle());
				domicilioDTO.setDepartamento(dto2.getDomicilio().getDepartamento());
				LocalidadDTO localidadDTO = new LocalidadDTO();
				localidadDTO.setId(dto2.getDomicilio().getLocalidad().getId());
				localidadDTO.setNombre(dto2.getDomicilio().getLocalidad().getNombre());
				domicilioDTO.setLocalidad(localidadDTO);
				domicilioDTO.setNumero(dto2.getDomicilio().getNumero());
				domicilioDTO.setPiso(dto2.getDomicilio().getPiso());
				domicilioDTO.setPropietario(usuarioDTO);
				dto.setDomicilio(domicilioDTO);
				List<DetalleDTO> detalle = new ArrayList<>();
				for (Detalle detalleInternal : dto2.getDetalle()) {
					DetalleDTO detalleDTO = new DetalleDTO();
					detalleDTO.setId(detalleInternal.getId());
					detalleDTO.setCantidad(detalleInternal.getCantidad());
					if (detalleInternal.getInsumo() != null) {
						InsumoDTO insumo = new InsumoDTO();
						insumo.setDescripcion(detalleInternal.getInsumo().getDescripcion());
						insumo.setEsInsumo(detalleInternal.getInsumo().isEsInsumo());
						insumo.setId(detalleInternal.getInsumo().getId());
						insumo.setStockActual(detalleInternal.getInsumo().getStockActual());
						insumo.setStockMaximo(detalleInternal.getInsumo().getStockMaximo());
						insumo.setStockMinimo(detalleInternal.getInsumo().getStockMinimo());
						insumo.setNombre(detalleInternal.getInsumo().getNombre());
						insumo.setPrecioCompra(detalleInternal.getInsumo().getPrecioCompra());
						insumo.setPrecioVenta(detalleInternal.getInsumo().getPrecioVenta());
						UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
						unidadMedida.setId(detalleInternal.getInsumo().getUnidadMedida().getId());
						unidadMedida.setNombre(detalleInternal.getInsumo().getUnidadMedida().getNombre());
						unidadMedida.setAbreviatura(detalleInternal.getInsumo().getUnidadMedida().getAbreviatura());
						insumo.setUnidadMedida(unidadMedida);
						detalleDTO.setInsumo(insumo);
					}
					if (detalleInternal.getPlato() != null) {
						PlatoDTO plato = new PlatoDTO();
						plato.setId(detalleInternal.getPlato().getId());
						plato.setNombre(detalleInternal.getPlato().getNombre());
						plato.setTiempoPreparacion(detalleInternal.getPlato().getTiempoPreparacion());
						plato.setPrecioVenta(detalleInternal.getPlato().getPrecioVenta());
						List<DetallePlatoDTO> detallesPlato = new ArrayList<>();
						for (DetallePlato platoDetalleInternal : detalleInternal.getPlato().getDetalle()) {
							DetallePlatoDTO temp = new DetallePlatoDTO();
							temp.setId(platoDetalleInternal.getId());
							temp.setCantidad(platoDetalleInternal.getCantidad());
							InsumoDTO insumoDetalle = new InsumoDTO();
							insumoDetalle.setDescripcion(platoDetalleInternal.getInsumo().getDescripcion());
							insumoDetalle.setEsInsumo(platoDetalleInternal.getInsumo().isEsInsumo());
							insumoDetalle.setId(platoDetalleInternal.getInsumo().getId());
							insumoDetalle.setStockActual(platoDetalleInternal.getInsumo().getStockActual());
							insumoDetalle.setStockMaximo(platoDetalleInternal.getInsumo().getStockMaximo());
							insumoDetalle.setStockMinimo(platoDetalleInternal.getInsumo().getStockMinimo());
							insumoDetalle.setNombre(platoDetalleInternal.getInsumo().getNombre());
							insumoDetalle.setPrecioCompra(platoDetalleInternal.getInsumo().getPrecioCompra());
							insumoDetalle.setPrecioVenta(platoDetalleInternal.getInsumo().getPrecioVenta());
							InsumoCategoriaDTO insumoCategoria = new InsumoCategoriaDTO();
							insumoCategoria.setId(platoDetalleInternal.getInsumo().getCategoria().getId());
							insumoCategoria.setNombre(platoDetalleInternal.getInsumo().getCategoria().getNombre());
							insumoCategoria
									.setDescripcion(platoDetalleInternal.getInsumo().getCategoria().getDescripcion());
							insumoDetalle.setCategoria(insumoCategoria);
							UnidadMedidaDTO unidadMedidaDetalle = new UnidadMedidaDTO();
							unidadMedidaDetalle.setId(platoDetalleInternal.getInsumo().getUnidadMedida().getId());
							unidadMedidaDetalle
									.setNombre(platoDetalleInternal.getInsumo().getUnidadMedida().getNombre());
							unidadMedidaDetalle.setAbreviatura(
									platoDetalleInternal.getInsumo().getUnidadMedida().getAbreviatura());
							insumoDetalle.setUnidadMedida(unidadMedidaDetalle);
							temp.setIngrediente(insumoDetalle);
							detallesPlato.add(temp);
						}
						plato.setDetalle(detallesPlato);
						detalleDTO.setPlato(plato);
					}
					detalle.add(detalleDTO);
				}
				dto.setDetalle(detalle);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("No existe el id");
		}
		return dto;
	}

	@Transactional
	public PedidoDTO save(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		try {
			pedido.setHoraEstimada(pedidoDTO.getHoraEstimada());
			pedido.setEnvioDelivery(pedidoDTO.isEnvioDelivery());
			pedido.setEliminado(pedidoDTO.isEliminado());
			Usuario user = new Usuario();
			user.setId(pedidoDTO.getUsuario().getId());
			pedido.setUsuario(user);
			Estado estado = new Estado();
			estado.setId(pedidoDTO.getEstado().getId());
			estado.setNombre(pedidoDTO.getEstado().getNombre());
			pedido.setEstado(estado);
			Domicilio domicilio = new Domicilio();
			domicilio.setId(pedido.getDomicilio().getId());
			pedido.setDomicilio(domicilio);
			List<Detalle> detalle = new ArrayList<>();
			for (DetalleDTO detalleDTO : pedidoDTO.getDetalle()) {
				Detalle detalleTemp = new Detalle();
				detalleTemp.setCantidad(detalleDTO.getCantidad());
				if (detalleDTO.getInsumo() != null) {
					Insumo insumo = new Insumo();
					insumo = insumoRepository.findById(detalleDTO.getInsumo().getId()).get();
					insumo.setStockActual((insumo.getStockActual()) - (detalleDTO.getCantidad()));
					insumoRepository.save(insumo);
					detalleTemp.setInsumo(insumo);
				}
				if (detalleDTO.getPlato() != null) {
					Plato plato = new Plato();
					// Proceso de stock
					plato = platoRepository.findById(detalleDTO.getPlato().getId()).get();
					System.out.println(plato.getId());
					for (DetallePlato platoDetalle : plato.getDetalle()) {
						Insumo insumo = new Insumo();
						insumo = insumoRepository.findById(platoDetalle.getInsumo().getId()).get();
						insumo.setStockActual((insumo.getStockActual())
								- ((detalleDTO.getCantidad()) * (platoDetalle.getCantidad())));
						insumoRepository.save(insumo);
					}
					plato.setId(detalleDTO.getPlato().getId());
					detalleTemp.setPlato(plato);
				}
				detalle.add(detalleTemp);
			}
			pedido.setDetalle(detalle);
			pedidoRepository.save(pedido);
			pedidoDTO.setId(pedido.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pedidoDTO;
	}

	@Transactional
	public PedidoDTO updateEstado(int id, PedidoDTO pedidoDTO, int estado) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		Pedido pedido = new Pedido();
		try {
			pedido = optional.get();
			Estado estadoObj = new Estado();
			estadoObj.setId(estado);
			pedido.setEstado(estadoObj);
			if (estado == 22) {
				String sDate1 = pedido.getHoraEstimada();
				Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(sDate1);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date1);
				calendar.add(Calendar.MINUTE, 10);
				Date fechaSalida = calendar.getTime();
				Format f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String strDate = f.format(fechaSalida);
				pedido.setHoraEstimada(strDate);
			}
			pedidoRepository.save(pedido);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pedidoDTO;
	}

	@Transactional
	public PedidoDTO update(PedidoDTO pedidoDTO, int id) {
		Optional<Pedido> optional = pedidoRepository.findById(id);
		Pedido pedido = new Pedido();
		try {
			pedido = optional.get();
			pedido.setHoraEstimada(pedidoDTO.getHoraEstimada());
			pedido.setEnvioDelivery(pedidoDTO.isEnvioDelivery());
			pedido.setEliminado(pedidoDTO.isEliminado());
			try {
				Usuario user = new Usuario();
				user.setId(pedidoDTO.getUsuario().getId());
				pedido.setUsuario(user);
				Estado estado = new Estado();
				estado.setId(pedidoDTO.getEstado().getId());
				pedido.setEstado(estado);
				Domicilio domicilio = new Domicilio();
				domicilio.setId(pedido.getDomicilio().getId());
				pedido.setDomicilio(domicilio);
				List<Detalle> detalle = new ArrayList<>();
				for (DetalleDTO detalleDTO : pedidoDTO.getDetalle()) {
					Detalle detalleTemp = new Detalle();
					detalleTemp.setId(detalleDTO.getId());
					detalle.add(detalleTemp);
				}
				pedido.setDetalle(pedido.getDetalle());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			pedidoRepository.save(pedido);
			pedidoDTO.setId(pedido.getId());
		} catch (Exception e) {
			System.out.println("Bad Request");
			pedidoDTO.setId(0);
		}
		return pedidoDTO;
	}

	@Transactional
	public boolean delete(int id) {
		try {
			pedidoRepository.deletePedidoById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Transactional
	public List<PedidoDTO> getPedidos() {
		List<PedidoDTO> result = new ArrayList<>();
		try {
			for (Pedido dto2 : pedidoRepository.findAll()) {
				PedidoDTO dto = new PedidoDTO();
				dto.setId(dto2.getId());
				dto.setEnvioDelivery(dto2.isEnvioDelivery());
				UsuarioDTO user = new UsuarioDTO();
				user.setId(dto2.getUsuario().getId());
				dto.setUsuario(user);
				DomicilioDTO domicilioDTO = new DomicilioDTO();
				domicilioDTO.setId(dto2.getDomicilio().getId());
				dto.setDomicilio(domicilioDTO);
				EstadoDTO estado = new EstadoDTO();
				estado.setId(dto2.getEstado().getId());
				estado.setNombre(dto2.getEstado().getNombre());
				dto.setEstado(estado);
				result.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
