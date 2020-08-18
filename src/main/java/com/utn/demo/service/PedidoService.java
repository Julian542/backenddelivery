package com.utn.demo.service;

import java.math.BigDecimal;
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
import com.utn.demo.dtos.DomicilioDTO;
import com.utn.demo.dtos.EstadoDTO;
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
import com.utn.demo.repository.DetalleRepository;
import com.utn.demo.repository.DomicilioRepository;
import com.utn.demo.repository.InsumoRepository;
import com.utn.demo.repository.PedidoRepository;
import com.utn.demo.repository.PlatoRepository;

@Service
public class PedidoService {

	private PedidoRepository pedidoRepository;
	private DetalleRepository detalleRepository;
	private DomicilioRepository domicilioRepository;
	private InsumoRepository insumoRepository;
	private PlatoRepository platoRepository;

	public PedidoService(PedidoRepository pedidoRepository, InsumoRepository insumoRepository,
			PlatoRepository platoRepository, DetalleRepository detalleRepository, DomicilioRepository domicilioRepository) {
		this.pedidoRepository = pedidoRepository;
		this.insumoRepository = insumoRepository;
		this.platoRepository = platoRepository;
		this.detalleRepository = detalleRepository;
		this.domicilioRepository = domicilioRepository;
	}
	@Transactional
	public List<PedidoDTO> getAll() {

		List<PedidoDTO> result = new ArrayList<>();

		for (Pedido dto2 : pedidoRepository.findAllMod()) {
			PedidoDTO dto = new PedidoDTO();
			dto.setId(dto2.getId());
			dto.setTiempoPreparacion(dto2.getTiempoPreparacion());
			dto.setHoraEstimada(dto2.getHoraEstimada());
			dto.setFecha(dto2.getFecha());
			dto.setEnvioDelivery(dto2.isEnvioDelivery());
			dto.setEliminado(dto.isEliminado());

			float data = 0;

			for (Detalle entity2 : detalleRepository.buscarPorPedido(dto2.getId())) {
				try {
					data += entity2.getPlato().getPrecioVenta() * entity2.getCantidad();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				try {
					data += entity2.getInsumo().getPrecioVenta() * entity2.getCantidad();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

			dto.setMonto(round(data, 2));

			try {

				UsuarioDTO user = new UsuarioDTO();
				user.setId(dto2.getUsuario().getId());
				user.setNombre(dto2.getUsuario().getNombre());
				user.setApellido(dto2.getUsuario().getApellido());
				user.setTelefono(dto2.getUsuario().getTelefono());
				List<DomicilioDTO> domiciliosdto = new ArrayList<DomicilioDTO>();
				for (Domicilio d : domicilioRepository.buscarPorUsuario(user.getId())) {
					DomicilioDTO dtodom = new DomicilioDTO();
					dtodom.setCalle(d.getCalle());
					dtodom.setDepartamento(d.getDepartamento());
					dtodom.setId(d.getId());
					//
					LocalidadDTO localidaddto = new LocalidadDTO();
					localidaddto.setId(d.getLocalidad().getId());
					localidaddto.setNombre(d.getLocalidad().getNombre());
					localidaddto.setEliminado(d.getLocalidad().isEliminado());
					//
					dtodom.setLocalidad(localidaddto);
					dtodom.setNumero(d.getNumero());
					dtodom.setPiso(d.getPiso());
					dtodom.setEliminado(d.isEliminado());
					domiciliosdto.add(dtodom);
				}
				user.setDomicilios(domiciliosdto);
				dto.setUsuario(user);
				

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

			try {
				DomicilioDTO dtodom = new DomicilioDTO();
				dtodom.setCalle(dto2.getDomicilio().getCalle());
				dtodom.setDepartamento(dto2.getDomicilio().getDepartamento());
				dtodom.setId(dto2.getDomicilio().getId());
				//
				LocalidadDTO localidaddto = new LocalidadDTO();
				localidaddto.setId(dto2.getDomicilio().getLocalidad().getId());
				localidaddto.setNombre(dto2.getDomicilio().getLocalidad().getNombre());
				localidaddto.setEliminado(dto2.getDomicilio().getLocalidad().isEliminado());
				//
				dtodom.setLocalidad(localidaddto);
				dtodom.setNumero(dto2.getDomicilio().getNumero());
				dtodom.setPiso(dto2.getDomicilio().getPiso());
				dtodom.setEliminado(dto2.getDomicilio().isEliminado());

				dto.setDomicilio(dtodom);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				EstadoDTO estado = new EstadoDTO();
				estado.setId(dto2.getEstado().getId());
				estado.setNombre(dto2.getEstado().getNombre());
				estado.setEliminado(dto2.getEstado().isEliminado());
				dto.setEstado(estado);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}


			result.add(dto);
		}

		return result;

	}
	@Transactional
	public List<PedidoDTO> getAllByUser(int id) {

		List<PedidoDTO> result = new ArrayList<>();

		for (Pedido dto2 : pedidoRepository.getAllByUser(id)) {
			PedidoDTO dto = new PedidoDTO();
			dto.setId(dto2.getId());
			dto.setTiempoPreparacion(dto2.getTiempoPreparacion());
			dto.setHoraEstimada(dto2.getHoraEstimada());
			dto.setFecha(dto2.getFecha());
			dto.setEnvioDelivery(dto2.isEnvioDelivery());
			dto.setEliminado(dto2.isEliminado());

			try {

				UsuarioDTO user = new UsuarioDTO();
				user.setId(dto2.getUsuario().getId());
				user.setNombre(dto2.getUsuario().getNombre());
				user.setApellido(dto2.getUsuario().getApellido());
				user.setTelefono(dto2.getUsuario().getTelefono());
				List<DomicilioDTO> domiciliosdto = new ArrayList<DomicilioDTO>();
				for (Domicilio d : domicilioRepository.buscarPorUsuario(user.getId())) {
					DomicilioDTO dtodom = new DomicilioDTO();
					dtodom.setCalle(d.getCalle());
					dtodom.setDepartamento(d.getDepartamento());
					dtodom.setId(d.getId());
					//
					LocalidadDTO localidaddto = new LocalidadDTO();
					localidaddto.setId(d.getLocalidad().getId());
					localidaddto.setNombre(d.getLocalidad().getNombre());
					localidaddto.setEliminado(d.getLocalidad().isEliminado());
					//
					dtodom.setLocalidad(localidaddto);
					dtodom.setNumero(d.getNumero());
					dtodom.setPiso(d.getPiso());
					dtodom.setEliminado(d.isEliminado());
					domiciliosdto.add(dtodom);
				}
				user.setDomicilios(domiciliosdto);
				dto.setUsuario(user);

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

			try {
				DomicilioDTO dtodom = new DomicilioDTO();
				dtodom.setCalle(dto2.getDomicilio().getCalle());
				dtodom.setDepartamento(dto2.getDomicilio().getDepartamento());
				dtodom.setId(dto2.getDomicilio().getId());
				//
				LocalidadDTO localidaddto = new LocalidadDTO();
				localidaddto.setId(dto2.getDomicilio().getLocalidad().getId());
				localidaddto.setNombre(dto2.getDomicilio().getLocalidad().getNombre());
				localidaddto.setEliminado(dto2.getDomicilio().getLocalidad().isEliminado());
				//
				dtodom.setLocalidad(localidaddto);
				dtodom.setNumero(dto2.getDomicilio().getNumero());
				dtodom.setPiso(dto2.getDomicilio().getPiso());
				dtodom.setEliminado(dto2.getDomicilio().isEliminado());

				dto.setDomicilio(dtodom);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}
	@Transactional
	public PedidoDTO getOne(int id) {


		PedidoDTO dto = new PedidoDTO();

		try {

			Pedido dto2 = pedidoRepository.findByIdMod(id);
			dto.setId(dto2.getId());
			dto.setTiempoPreparacion(dto2.getTiempoPreparacion());
			dto.setHoraEstimada(dto2.getHoraEstimada());
			dto.setFecha(dto2.getFecha());
			dto.setEnvioDelivery(dto2.isEnvioDelivery());
			dto.setEliminado(dto2.isEliminado());

			try {

				UsuarioDTO user = new UsuarioDTO();
				user.setId(dto2.getUsuario().getId());
				user.setNombre(dto2.getUsuario().getNombre());
				user.setApellido(dto2.getUsuario().getApellido());
				user.setTelefono(dto2.getUsuario().getTelefono());
				List<DomicilioDTO> domiciliosdto = new ArrayList<DomicilioDTO>();
				for (Domicilio d : domicilioRepository.buscarPorUsuario(user.getId())) {
					DomicilioDTO dtodom = new DomicilioDTO();
					dtodom.setCalle(d.getCalle());
					dtodom.setDepartamento(d.getDepartamento());
					dtodom.setId(d.getId());
					//
					LocalidadDTO localidaddto = new LocalidadDTO();
					localidaddto.setId(d.getLocalidad().getId());
					localidaddto.setNombre(d.getLocalidad().getNombre());
					localidaddto.setEliminado(d.getLocalidad().isEliminado());
					//
					dtodom.setLocalidad(localidaddto);
					dtodom.setNumero(d.getNumero());
					dtodom.setPiso(d.getPiso());
					dtodom.setEliminado(d.isEliminado());
					domiciliosdto.add(dtodom);
				}
				user.setDomicilios(domiciliosdto);
				dto.setUsuario(user);

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}
			
			try {
				DomicilioDTO dtodom = new DomicilioDTO();
				dtodom.setCalle(dto2.getDomicilio().getCalle());
				dtodom.setDepartamento(dto2.getDomicilio().getDepartamento());
				dtodom.setId(dto2.getDomicilio().getId());
				//
				LocalidadDTO localidaddto = new LocalidadDTO();
				localidaddto.setId(dto2.getDomicilio().getLocalidad().getId());
				localidaddto.setNombre(dto2.getDomicilio().getLocalidad().getNombre());
				localidaddto.setEliminado(dto2.getDomicilio().getLocalidad().isEliminado());
				//
				dtodom.setLocalidad(localidaddto);
				dtodom.setNumero(dto2.getDomicilio().getNumero());
				dtodom.setPiso(dto2.getDomicilio().getPiso());
				dtodom.setEliminado(dto2.getDomicilio().isEliminado());

				dto.setDomicilio(dtodom);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				EstadoDTO estado = new EstadoDTO();
				estado.setId(dto2.getEstado().getId());
				estado.setNombre(dto2.getEstado().getNombre());
				dto.setEstado(estado);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			float data = 0;

			for (Detalle entity2 : detalleRepository.buscarPorPedido(dto2.getId())) {
				try {
					data += entity2.getPlato().getPrecioVenta() * entity2.getCantidad();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				try {
					data += entity2.getInsumo().getPrecioVenta() * entity2.getCantidad();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

			dto.setMonto(round(data, 2));

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return dto;

	}
	@Transactional
	public PedidoDTO save(PedidoDTO pedidoDTO) {

		Pedido pedido = new Pedido();

		pedido.setTiempoPreparacion(pedidoDTO.getTiempoPreparacion());
		pedido.setHoraEstimada(pedidoDTO.getHoraEstimada());
		pedido.setFecha(pedidoDTO.getFecha());
		pedido.setEnvioDelivery(pedidoDTO.isEnvioDelivery());
		pedido.setEliminado(pedidoDTO.isEliminado());

		try {

			Usuario user = new Usuario();
			user.setId(pedidoDTO.getUsuario().getId());
			pedido.setUsuario(user);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		
		try {

			Domicilio domicilio = new Domicilio();
			domicilio.setId(pedidoDTO.getDomicilio().getId());
			pedido.setDomicilio(domicilio);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		

		try {
			Estado estado = new Estado();
			estado.setId(pedidoDTO.getEstado().getId());
			pedido.setEstado(estado);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		pedidoRepository.save(pedido);

		pedidoDTO.setId(pedido.getId());
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
				// System.out.println(sDate1+"\t"+date1);

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
		}

		return pedidoDTO;

	}
	@Transactional
	public PedidoDTO update(PedidoDTO pedidoDTO, int id) {

		Optional<Pedido> optional = pedidoRepository.findById(id);
		Pedido pedido = new Pedido();

		try {

			pedido = optional.get();

			pedido.setTiempoPreparacion(pedidoDTO.getTiempoPreparacion());
			pedido.setHoraEstimada(pedidoDTO.getHoraEstimada());
			pedido.setFecha(pedidoDTO.getFecha());
			pedido.setEnvioDelivery(pedidoDTO.isEnvioDelivery());
			pedido.setEliminado(pedidoDTO.isEliminado());

			try {

				Usuario user = new Usuario();
				user.setId(pedidoDTO.getUsuario().getId());
				pedido.setUsuario(user);

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}
			
			try {

				Domicilio domicilio = new Domicilio();
				domicilio.setId(pedidoDTO.getDomicilio().getId());
				pedido.setDomicilio(domicilio);

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}

			try {
				Estado estado = new Estado();
				estado.setId(pedidoDTO.getEstado().getId());
				pedido.setEstado(estado);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}


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
				dto.setTiempoPreparacion(dto2.getTiempoPreparacion());
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

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

}
