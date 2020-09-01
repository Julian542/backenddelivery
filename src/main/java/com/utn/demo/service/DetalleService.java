package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.DetalleDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.entity.Detalle;
import com.utn.demo.entity.DetallePlato;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.InsumoCategoria;
import com.utn.demo.entity.Pedido;
import com.utn.demo.entity.Plato;
import com.utn.demo.repository.DetallePlatoRepository;
import com.utn.demo.repository.DetalleRepository;
import com.utn.demo.repository.InsumoRepository;
import com.utn.demo.repository.PlatoRepository;

@Service
public class DetalleService {

	private DetalleRepository detalleRepository;
	private DetallePlatoRepository detallePlatoRepository;
	private InsumoRepository insumoRepository;
	private PlatoRepository platoRepository;

	public DetalleService(DetalleRepository detalleRepository, InsumoRepository insumoRepository,
			DetallePlatoRepository detallePlatoRepository, PlatoRepository platoRepository) {
		this.detalleRepository = detalleRepository;
		this.detallePlatoRepository = detallePlatoRepository;
		this.insumoRepository = insumoRepository;
		this.platoRepository = platoRepository;
	}

	@Transactional
	public List<DetalleDTO> getAll() {

		List<DetalleDTO> result = new ArrayList<>();

		for (Detalle entity : detalleRepository.findAllMod()) {
			DetalleDTO dto = new DetalleDTO();
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());
			dto.setFecha(entity.getFecha());
			dto.setEliminado(entity.isEliminado());

			try {
				PlatoDTO plato = new PlatoDTO();
				plato.setId(entity.getPlato().getId());
				plato.setNombre(entity.getPlato().getNombre());
				plato.setTiempoPreparacion(entity.getPlato().getTiempoPreparacion());
				plato.setPrecioVenta(entity.getPlato().getPrecioVenta());

				dto.setPlato(plato);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				InsumoDTO insumo = new InsumoDTO();
				insumo.setId(entity.getInsumo().getId());
				insumo.setNombre(entity.getInsumo().getNombre());
				insumo.setDescripcion(entity.getInsumo().getDescripcion());
				insumo.setPrecioCompra(entity.getInsumo().getPrecioCompra());
				insumo.setStockActual(entity.getInsumo().getStockActual());
				insumo.setStockMinimo(entity.getInsumo().getStockMinimo());
				insumo.setStockMaximo(entity.getInsumo().getStockMaximo());
				insumo.setEsInsumo(entity.getInsumo().isEsInsumo());
				insumo.setPrecioVenta(entity.getInsumo().getPrecioVenta());
				dto.setInsumo(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				PedidoDTO pedido = new PedidoDTO();
				pedido.setId(entity.getPedido().getId());

				dto.setPedido(pedido);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	@Transactional
	public DetalleDTO getOne(int id) {

		DetalleDTO dto = new DetalleDTO();

		try {

			Detalle entity = detalleRepository.findByIdMod(id);
			dto.setId(entity.getId());
			dto.setFecha(entity.getFecha());
			dto.setCantidad(entity.getCantidad());
			dto.setEliminado(entity.isEliminado());

			try {
				PlatoDTO plato = new PlatoDTO();
				plato.setId(entity.getPlato().getId());
				plato.setNombre(entity.getPlato().getNombre());
				plato.setTiempoPreparacion(entity.getPlato().getTiempoPreparacion());
				plato.setPrecioVenta(entity.getPlato().getPrecioVenta());
				dto.setPlato(plato);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				InsumoDTO insumo = new InsumoDTO();
				insumo.setId(entity.getInsumo().getId());
				insumo.setNombre(entity.getInsumo().getNombre());
				insumo.setDescripcion(entity.getInsumo().getDescripcion());
				insumo.setPrecioCompra(entity.getInsumo().getPrecioCompra());
				insumo.setStockActual(entity.getInsumo().getStockActual());
				insumo.setStockMinimo(entity.getInsumo().getStockMinimo());
				insumo.setStockMaximo(entity.getInsumo().getStockMaximo());
				insumo.setEsInsumo(entity.getInsumo().isEsInsumo());
				insumo.setPrecioVenta(entity.getInsumo().getPrecioVenta());
				dto.setInsumo(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				PedidoDTO pedido = new PedidoDTO();
				pedido.setId(entity.getPedido().getId());

				dto.setPedido(pedido);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return dto;

	}

	@Transactional
	public DetalleDTO save(DetalleDTO detalleDTO) {

		Detalle detalle = new Detalle();

		detalle.setCantidad(detalleDTO.getCantidad());
		detalle.setFecha(detalleDTO.getFecha());
		detalle.setEliminado(detalleDTO.isEliminado());

		try {
			Plato plato = new Plato();
			plato.setId(detalleDTO.getPlato().getId());
			detalle.setPlato(plato);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {

			Insumo insumo = new Insumo();
			insumo.setId(detalleDTO.getInsumo().getId());
			detalle.setInsumo(insumo);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			Pedido pedido = new Pedido();
			pedido.setId(detalleDTO.getPedido().getId());

			detalle.setPedido(pedido);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		detalleRepository.save(detalle);

		detalleDTO.setId(detalle.getId());
		return detalleDTO;

	}

	@Transactional
	public DetalleDTO update(int id, DetalleDTO detalleDTO) {

		Optional<Detalle> optional = detalleRepository.findById(id);
		Detalle detalle = new Detalle();

		try {

			detalle = optional.get();

			detalle.setCantidad(detalleDTO.getCantidad());
			detalle.setFecha(detalleDTO.getFecha());
			detalle.setEliminado(detalleDTO.isEliminado());

			try {
				Plato plato = new Plato();
				plato.setId(detalleDTO.getPlato().getId());
				detalle.setPlato(plato);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				Insumo insumo = new Insumo();
				insumo.setId(detalleDTO.getInsumo().getId());
				detalle.setInsumo(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				Pedido pedido = new Pedido();
				pedido.setId(detalleDTO.getPedido().getId());

				detalle.setPedido(pedido);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			detalleRepository.save(detalle);

			detalleDTO.setId(detalle.getId());

		} catch (Exception e) {

			System.out.println("Bad Request");
			detalleDTO.setId(0);

		}

		return detalleDTO;

	}

	@Transactional
	public DetalleDTO updatePlato(int id, DetalleDTO detalleDTO) {

		Optional<Detalle> optional = detalleRepository.findById(id);
		Detalle detalle = new Detalle();
		boolean faltaStock = false;

		try {

			detalle = optional.get();

			detalle.setCantidad(detalleDTO.getCantidad());
			detalle.setFecha(detalleDTO.getFecha());
			detalle.setEliminado(detalleDTO.isEliminado());

			try {
				Plato plato = new Plato();
				plato.setId(detalleDTO.getPlato().getId());
				if (detalle.getPlato().getId() != detalleDTO.getPlato().getId()) {
					for (DetallePlato entity2 : detallePlatoRepository.getAllByUser(detalle.getPlato().getId())) {

						DetallePlato platoDetalle = new DetallePlato();

						platoDetalle.setId(entity2.getId());
						platoDetalle.setCantidad(entity2.getCantidad());
						platoDetalle.setEliminado(entity2.isEliminado());
						Insumo insumo = new Insumo();
						insumo.setId(entity2.getInsumo().getId());

						insumo.setNombre(entity2.getInsumo().getNombre());
						insumo.setDescripcion(entity2.getInsumo().getDescripcion());
						insumo.setPrecioCompra(entity2.getInsumo().getPrecioCompra());
						insumo.setStockMinimo(entity2.getInsumo().getStockMinimo());
						insumo.setStockMaximo(entity2.getInsumo().getStockMaximo());
						insumo.setEsInsumo(entity2.getInsumo().isEsInsumo());
						insumo.setPrecioVenta(entity2.getInsumo().getPrecioVenta());
						insumo.setEliminado(entity2.getInsumo().isEliminado());
						insumo.setUnidadMedida(entity2.getInsumo().getUnidadMedida());

						try {

							InsumoCategoria articuloCategoria = new InsumoCategoria();
							articuloCategoria.setId(entity2.getInsumo().getCategoria().getId());
							articuloCategoria.setNombre(entity2.getInsumo().getCategoria().getNombre());
							articuloCategoria.setDescripcion(entity2.getInsumo().getCategoria().getDescripcion());
							articuloCategoria.setEliminado(entity2.getInsumo().getCategoria().isEliminado());
							insumo.setCategoria(articuloCategoria);

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						insumo.setStockActual(entity2.getInsumo().getStockActual()
								+ ((entity2.getCantidad() * 0.001) * detalle.getCantidad()));
						platoDetalle.setInsumo(insumo);

						insumoRepository.save(insumo);
					}
				}
				for (DetallePlato entity2 : detallePlatoRepository.getAllByUser(detalleDTO.getPlato().getId())) {

					DetallePlato platoDetalle = new DetallePlato();

					platoDetalle.setId(entity2.getId());
					platoDetalle.setCantidad(entity2.getCantidad());
					platoDetalle.setEliminado(entity2.isEliminado());
					Insumo insumo = new Insumo();
					insumo.setId(entity2.getInsumo().getId());

					insumo.setNombre(entity2.getInsumo().getNombre());
					insumo.setDescripcion(entity2.getInsumo().getDescripcion());
					insumo.setPrecioCompra(entity2.getInsumo().getPrecioCompra());
					insumo.setStockMinimo(entity2.getInsumo().getStockMinimo());
					insumo.setStockMaximo(entity2.getInsumo().getStockMaximo());
					insumo.setEsInsumo(entity2.getInsumo().isEsInsumo());
					insumo.setPrecioVenta(entity2.getInsumo().getPrecioVenta());
					insumo.setEliminado(entity2.getInsumo().isEliminado());
					insumo.setUnidadMedida(entity2.getInsumo().getUnidadMedida());

					try {

						InsumoCategoria articuloCategoria = new InsumoCategoria();
						articuloCategoria.setId(entity2.getInsumo().getCategoria().getId());
						articuloCategoria.setNombre(entity2.getInsumo().getCategoria().getNombre());
						articuloCategoria.setDescripcion(entity2.getInsumo().getCategoria().getDescripcion());
						articuloCategoria.setEliminado(entity2.getInsumo().getCategoria().isEliminado());
						insumo.setCategoria(articuloCategoria);

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					insumo.setStockActual(entity2.getInsumo().getStockActual()
							- ((entity2.getCantidad() * 0.001) * detalleDTO.getCantidad()));
					platoDetalle.setInsumo(insumo);
					if (entity2.getInsumo()
							.getStockActual() < ((entity2.getCantidad() * 0.001) * detalleDTO.getCantidad())) {
						faltaStock = true;
					}

					insumoRepository.save(insumo);
				}

				detalle.setPlato(plato);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			if (faltaStock) {
				try {
					for (DetallePlato entity2 : detallePlatoRepository.getAllByUser(detalleDTO.getPlato().getId())) {

						DetallePlato platoDetalle = new DetallePlato();

						platoDetalle.setId(entity2.getId());
						platoDetalle.setCantidad(entity2.getCantidad());
						platoDetalle.setEliminado(entity2.isEliminado());
						Insumo insumo = new Insumo();
						insumo.setId(entity2.getInsumo().getId());

						insumo.setNombre(entity2.getInsumo().getNombre());
						insumo.setDescripcion(entity2.getInsumo().getDescripcion());
						insumo.setPrecioCompra(entity2.getInsumo().getPrecioCompra());
						insumo.setStockMinimo(entity2.getInsumo().getStockMinimo());
						insumo.setStockMaximo(entity2.getInsumo().getStockMaximo());
						insumo.setEsInsumo(entity2.getInsumo().isEsInsumo());
						insumo.setPrecioVenta(entity2.getInsumo().getPrecioVenta());
						insumo.setEliminado(entity2.getInsumo().isEliminado());
						insumo.setUnidadMedida(entity2.getInsumo().getUnidadMedida());

						try {

							InsumoCategoria articuloCategoria = new InsumoCategoria();
							articuloCategoria.setId(entity2.getInsumo().getCategoria().getId());
							articuloCategoria.setNombre(entity2.getInsumo().getCategoria().getNombre());
							articuloCategoria.setDescripcion(entity2.getInsumo().getCategoria().getDescripcion());
							articuloCategoria.setEliminado(entity2.getInsumo().getCategoria().isEliminado());
							insumo.setCategoria(articuloCategoria);

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						insumo.setStockActual(entity2.getInsumo().getStockActual()
								+ ((entity2.getCantidad() * 0.001) * detalleDTO.getCantidad()));
						platoDetalle.setInsumo(insumo);

						insumoRepository.save(insumo);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			try {
				Insumo insumo = new Insumo();
				insumo.setId(detalleDTO.getInsumo().getId());
				detalle.setInsumo(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				Pedido pedido = new Pedido();
				pedido.setId(detalleDTO.getPedido().getId());

				detalle.setPedido(pedido);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			if (faltaStock) {
				detalleDTO = null;
				return detalleDTO;
			}

			detalleRepository.save(detalle);

			detalleDTO.setId(detalle.getId());

		} catch (Exception e) {

			System.out.println("Bad Request");
			detalleDTO.setId(0);

		}

		return detalleDTO;

	}

	@Transactional
	public List<DetalleDTO> buscarPorPedido(int id) {

		List<DetalleDTO> result = new ArrayList<>();

		for (Detalle entity : detalleRepository.buscarPorPedido(id)) {
			DetalleDTO dto = new DetalleDTO();
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());
			dto.setFecha(entity.getFecha());
			dto.setEliminado(entity.isEliminado());

			try {
				PlatoDTO plato = new PlatoDTO();
				plato.setId(entity.getPlato().getId());
				plato.setNombre(entity.getPlato().getNombre());
				plato.setTiempoPreparacion(entity.getPlato().getTiempoPreparacion());
				plato.setPrecioVenta(entity.getPlato().getPrecioVenta());
				dto.setPlato(plato);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				InsumoDTO insumo = new InsumoDTO();
				insumo.setId(entity.getInsumo().getId());
				insumo.setNombre(entity.getInsumo().getNombre());
				insumo.setDescripcion(entity.getInsumo().getDescripcion());
				insumo.setPrecioCompra(entity.getInsumo().getPrecioCompra());
				insumo.setStockActual(entity.getInsumo().getStockActual());
				insumo.setStockMinimo(entity.getInsumo().getStockMinimo());
				insumo.setStockMaximo(entity.getInsumo().getStockMaximo());
				insumo.setEsInsumo(entity.getInsumo().isEsInsumo());
				insumo.setPrecioVenta(entity.getInsumo().getPrecioVenta());
				dto.setInsumo(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				PedidoDTO pedido = new PedidoDTO();
				pedido.setId(entity.getPedido().getId());

				dto.setPedido(pedido);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	@Transactional
	public List<DetalleDTO> buscarPorPlato(int id, int id2) {

		List<DetalleDTO> result = new ArrayList<>();

		for (Detalle entity : detalleRepository.buscarPorPlato(id, id2)) {
			DetalleDTO dto = new DetalleDTO();
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());
			dto.setFecha(entity.getFecha());
			dto.setEliminado(entity.isEliminado());

			try {
				PlatoDTO plato = new PlatoDTO();
				plato.setId(entity.getPlato().getId());
				plato.setNombre(entity.getPlato().getNombre());
				plato.setTiempoPreparacion(entity.getPlato().getTiempoPreparacion());
				plato.setPrecioVenta(entity.getPlato().getPrecioVenta());
				dto.setPlato(plato);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				InsumoDTO insumo = new InsumoDTO();
				insumo.setId(entity.getInsumo().getId());
				insumo.setNombre(entity.getInsumo().getNombre());
				insumo.setDescripcion(entity.getInsumo().getDescripcion());
				insumo.setPrecioCompra(entity.getInsumo().getPrecioCompra());
				insumo.setStockActual(entity.getInsumo().getStockActual());
				insumo.setStockMinimo(entity.getInsumo().getStockMinimo());
				insumo.setStockMaximo(entity.getInsumo().getStockMaximo());
				insumo.setEsInsumo(entity.getInsumo().isEsInsumo());
				insumo.setPrecioVenta(entity.getInsumo().getPrecioVenta());
				dto.setInsumo(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				PedidoDTO pedido = new PedidoDTO();
				pedido.setId(entity.getPedido().getId());

				dto.setPedido(pedido);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	@Transactional
	public List<DetalleDTO> buscarPorInsumo(int id, int id2) {

		List<DetalleDTO> result = new ArrayList<>();

		for (Detalle entity : detalleRepository.buscarPorInsumo(id, id2)) {
			DetalleDTO dto = new DetalleDTO();
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());
			dto.setFecha(entity.getFecha());
			dto.setEliminado(entity.isEliminado());

			try {
				PlatoDTO plato = new PlatoDTO();
				plato.setId(entity.getPlato().getId());
				plato.setNombre(entity.getPlato().getNombre());
				plato.setTiempoPreparacion(entity.getPlato().getTiempoPreparacion());
				plato.setPrecioVenta(entity.getPlato().getPrecioVenta());
				dto.setPlato(plato);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				InsumoDTO insumo = new InsumoDTO();
				insumo.setId(entity.getInsumo().getId());
				insumo.setNombre(entity.getInsumo().getNombre());
				insumo.setDescripcion(entity.getInsumo().getDescripcion());
				insumo.setPrecioCompra(entity.getInsumo().getPrecioCompra());
				insumo.setStockActual(entity.getInsumo().getStockActual());
				insumo.setStockMinimo(entity.getInsumo().getStockMinimo());
				insumo.setStockMaximo(entity.getInsumo().getStockMaximo());
				insumo.setEsInsumo(entity.getInsumo().isEsInsumo());
				insumo.setPrecioVenta(entity.getInsumo().getPrecioVenta());
				dto.setInsumo(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				PedidoDTO pedido = new PedidoDTO();
				pedido.setId(entity.getPedido().getId());

				dto.setPedido(pedido);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	public boolean delete(int id) {
		try {
			detalleRepository.deleteDetalleById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public Boolean descontarPlatos(int id) {
		Boolean seDesconto = true;
		try {
			for (Detalle detalle : detalleRepository.buscarPorPedido(id)) {
				if (detalle.getPlato() != null) {
					Plato plato = platoRepository.getOne(detalle.getPlato().getId());
					plato.setCantidadVendida(plato.getCantidadVendida() + detalle.getCantidad());
					platoRepository.save(plato);
					for (DetallePlato detPlato : detallePlatoRepository.findAllPorPlato(detalle.getPlato().getId())) {
						Insumo insumo = new Insumo();
						insumo = insumoRepository.getOne(detPlato.getInsumo().getId());
						if ((insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("kg")
								&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("kg"))
								|| (insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("l")
										&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("l"))
								|| (insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("g")
										&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("g"))
								|| (insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("ml")
										&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("ml"))
								|| insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("u")) {
							double number = Math.round(
									(insumo.getStockActual() - (detPlato.getCantidad() * detalle.getCantidad())) * 100);
							double temporal = number / 100;
							insumo.setStockActual(temporal);

						} else if ((insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("kg")
								&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("g"))
								|| (insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("l")
										&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("ml"))) {
							double number = Math.round((((insumo.getStockActual() * 1000)
									- (detPlato.getCantidad() * detalle.getCantidad())) / 1000) * 100);
							double temporal = number / 100;
							insumo.setStockActual(temporal);

						} else if ((insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("g")
								&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("k"))
								|| (insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("ml")
										&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("l"))) {
							double number = Math.round(((insumo.getStockActual()
									- ((detPlato.getCantidad() * detalle.getCantidad()) / 1000)) * 1000) * 100);
							double temporal = number / 100;
							insumo.setStockActual(temporal);

						}
						insumoRepository.save(insumo);
					}
				}
				if (detalle.getInsumo() != null) {
					Insumo insumoP = new Insumo();
					insumoP = insumoRepository.getOne(detalle.getInsumo().getId());
					double number = Math.round((insumoP.getStockActual() - detalle.getCantidad()) * 100);
					double temporal = number / 100;
					insumoP.setStockActual(temporal);
					insumoRepository.save(insumoP);
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return seDesconto;
	}
}
