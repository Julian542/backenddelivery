package com.utn.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.utn.demo.dtos.DetallePlatoDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.PlatoCategoriaDTO;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.entity.DetallePlato;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.Plato;
import com.utn.demo.entity.PlatoCategoria;
import com.utn.demo.repository.DetallePlatoRepository;
import com.utn.demo.repository.InsumoRepository;
import com.utn.demo.repository.PlatoRepository;

@Service
public class PlatoService {

	private PlatoRepository platoRepository;
	private DetallePlatoRepository detallePlatoRepository;
	private InsumoRepository insumoRepository;

	public PlatoService(PlatoRepository platoRepository, DetallePlatoRepository detallePlatoRepository,
			InsumoRepository insumoRepository) {
		this.platoRepository = platoRepository;
		this.detallePlatoRepository = detallePlatoRepository;
		this.insumoRepository = insumoRepository;
	}

	@Transactional
	public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {

		File archivo = new File("static\\images\\" + file.getOriginalFilename());
		if (archivo.exists()) {
			System.out.println("Ya existe esta imagen");
			return archivo.getAbsolutePath();
		} else {
			System.out.println("No existe esta imagen");
			file.transferTo(archivo);
			return archivo.getAbsolutePath();
		}
	}

	@Transactional
	public List<PlatoDTO> platosCategoria(String categoria) {
		List<PlatoDTO> result = new ArrayList<>();
		List<Plato> entidades = platoRepository.platosCategoria(categoria);
		for (Plato entity : entidades) {
			PlatoDTO dto = new PlatoDTO();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setTiempoPreparacion(entity.getTiempoPreparacion());
			dto.setImagen(entity.getImagen());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCosto(entity.getPrecioCosto());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setCantidadVendida(entity.getCantidadVendida());
			dto.setEliminado(entity.isEliminado());
			try {
				PlatoCategoriaDTO platoCategoria = new PlatoCategoriaDTO();
				platoCategoria.setId(entity.getCategoria().getId());
				platoCategoria.setNombre(entity.getCategoria().getNombre());
				platoCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				platoCategoria.setEliminado(entity.getCategoria().isEliminado());
				dto.setCategoria(platoCategoria);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			result.add(dto);
		}
		return result;
	}

	@Transactional
	public List<PlatoDTO> getPlatoPorCategoria(int id) {

		List<PlatoDTO> result = new ArrayList<>();

		for (Plato entity : platoRepository.findPlatoPorCategoria(id)) {

			PlatoDTO dto = new PlatoDTO();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setTiempoPreparacion(entity.getTiempoPreparacion());
			dto.setImagen(entity.getImagen());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCosto(entity.getPrecioCosto());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setCantidadVendida(entity.getCantidadVendida());
			dto.setEliminado(entity.isEliminado());

			List<DetallePlatoDTO> ingredientes = new ArrayList<DetallePlatoDTO>();
			for (DetallePlato entity2 : detallePlatoRepository.getAllByUser(entity.getId())) {
				try {
					DetallePlatoDTO ingrediente = new DetallePlatoDTO();
					InsumoDTO insumo = new InsumoDTO();

					insumo.setId(entity2.getInsumo().getId());
					insumo.setNombre(entity2.getInsumo().getNombre());
					insumo.setDescripcion(entity2.getInsumo().getDescripcion());
					insumo.setPrecioCompra(entity2.getInsumo().getPrecioCompra());
					insumo.setStockActual(entity2.getInsumo().getStockActual());
					insumo.setStockMinimo(entity2.getInsumo().getStockMinimo());
					insumo.setStockMaximo(entity2.getInsumo().getStockMaximo());
					insumo.setEsInsumo(entity2.getInsumo().isEsInsumo());
					insumo.setPrecioVenta(entity2.getInsumo().getPrecioVenta());
					insumo.setEliminado(entity2.getInsumo().isEliminado());

					UnidadMedidaDTO um = new UnidadMedidaDTO();
					um.setId(entity2.getInsumo().getUnidadMedida().getId());
					um.setNombre(entity2.getInsumo().getUnidadMedida().getNombre());
					um.setAbreviatura(entity2.getInsumo().getUnidadMedida().getAbreviatura());
					um.setEliminado(entity2.getInsumo().getUnidadMedida().isEliminado());

					insumo.setUnidadMedida(um);

					ingrediente.setIngrediente(insumo);
					ingrediente.setCantidad(entity2.getCantidad());
					ingrediente.setId(entity2.getId());
					ingrediente.setEliminado(entity2.isEliminado());

					ingredientes.add(ingrediente);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			dto.setDetalle(ingredientes);

			try {
				PlatoCategoriaDTO platoCategoria = new PlatoCategoriaDTO();
				platoCategoria.setId(entity.getCategoria().getId());
				platoCategoria.setNombre(entity.getCategoria().getNombre());
				platoCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				platoCategoria.setEliminado(entity.getCategoria().isEliminado());
				dto.setCategoria(platoCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	@Transactional
	public List<PlatoDTO> getAll() {

		List<PlatoDTO> result = new ArrayList<>();

		for (Plato entity : platoRepository.findAllMod()) {

			PlatoDTO dto = new PlatoDTO();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setTiempoPreparacion(entity.getTiempoPreparacion());
			dto.setImagen(entity.getImagen());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCosto(entity.getPrecioCosto());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setCantidadVendida(entity.getCantidadVendida());
			dto.setEliminado(entity.isEliminado());

			List<DetallePlatoDTO> ingredientes = new ArrayList<DetallePlatoDTO>();
			for (DetallePlato entity2 : detallePlatoRepository.getAllByUser(entity.getId())) {
				try {
					DetallePlatoDTO ingrediente = new DetallePlatoDTO();
					InsumoDTO insumo = new InsumoDTO();

					insumo.setId(entity2.getInsumo().getId());
					insumo.setNombre(entity2.getInsumo().getNombre());
					insumo.setDescripcion(entity2.getInsumo().getDescripcion());
					insumo.setPrecioCompra(entity2.getInsumo().getPrecioCompra());
					insumo.setStockActual(entity2.getInsumo().getStockActual());
					insumo.setStockMinimo(entity2.getInsumo().getStockMinimo());
					insumo.setStockMaximo(entity2.getInsumo().getStockMaximo());
					insumo.setEsInsumo(entity2.getInsumo().isEsInsumo());
					insumo.setPrecioVenta(entity2.getInsumo().getPrecioVenta());
					insumo.setEliminado(entity2.getInsumo().isEliminado());

					UnidadMedidaDTO um = new UnidadMedidaDTO();
					um.setId(entity2.getInsumo().getUnidadMedida().getId());
					um.setNombre(entity2.getInsumo().getUnidadMedida().getNombre());
					um.setAbreviatura(entity2.getInsumo().getUnidadMedida().getAbreviatura());
					um.setEliminado(entity2.getInsumo().getUnidadMedida().isEliminado());

					insumo.setUnidadMedida(um);

					ingrediente.setIngrediente(insumo);
					ingrediente.setCantidad(entity2.getCantidad());
					ingrediente.setId(entity2.getId());
					UnidadMedidaDTO um2 = new UnidadMedidaDTO();
					try {
						um2.setId(entity2.getInsumo().getUnidadMedida().getId());
						um2.setNombre(entity2.getInsumo().getUnidadMedida().getNombre());
						um2.setAbreviatura(entity2.getInsumo().getUnidadMedida().getAbreviatura());
						um2.setEliminado(entity2.getInsumo().getUnidadMedida().isEliminado());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					ingrediente.setUnidadMedida(um2);
					ingrediente.setEliminado(entity2.isEliminado());

					ingredientes.add(ingrediente);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			dto.setDetalle(ingredientes);

			try {
				PlatoCategoriaDTO platoCategoria = new PlatoCategoriaDTO();
				platoCategoria.setId(entity.getCategoria().getId());
				platoCategoria.setNombre(entity.getCategoria().getNombre());
				platoCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				platoCategoria.setEliminado(entity.getCategoria().isEliminado());
				dto.setCategoria(platoCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	@Transactional
	public PlatoDTO getOne(int id) {

		PlatoDTO dto = new PlatoDTO();

		try {

			Plato entity = platoRepository.findByIdMod(id);
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setTiempoPreparacion(entity.getTiempoPreparacion());
			dto.setImagen(entity.getImagen());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCosto(entity.getPrecioCosto());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setCantidadVendida(entity.getCantidadVendida());
			dto.setEliminado(entity.isEliminado());

			List<DetallePlatoDTO> ingredientes = new ArrayList<DetallePlatoDTO>();
			for (DetallePlato entity2 : detallePlatoRepository.getAllByUser(entity.getId())) {
				try {
					DetallePlatoDTO ingrediente = new DetallePlatoDTO();
					InsumoDTO insumo = new InsumoDTO();

					insumo.setId(entity2.getInsumo().getId());
					insumo.setNombre(entity2.getInsumo().getNombre());
					insumo.setDescripcion(entity2.getInsumo().getDescripcion());
					insumo.setPrecioCompra(entity2.getInsumo().getPrecioCompra());
					insumo.setStockActual(entity2.getInsumo().getStockActual());
					insumo.setStockMinimo(entity2.getInsumo().getStockMinimo());
					insumo.setStockMaximo(entity2.getInsumo().getStockMaximo());
					insumo.setEsInsumo(entity2.getInsumo().isEsInsumo());
					insumo.setPrecioVenta(entity2.getInsumo().getPrecioVenta());
					insumo.setEliminado(entity2.getInsumo().isEliminado());

					UnidadMedidaDTO um = new UnidadMedidaDTO();
					um.setId(entity2.getInsumo().getUnidadMedida().getId());
					um.setNombre(entity2.getInsumo().getUnidadMedida().getNombre());
					um.setAbreviatura(entity2.getInsumo().getUnidadMedida().getAbreviatura());
					um.setEliminado(entity2.getInsumo().getUnidadMedida().isEliminado());

					insumo.setUnidadMedida(um);

					ingrediente.setIngrediente(insumo);
					ingrediente.setCantidad(entity2.getCantidad());
					ingrediente.setId(entity2.getId());
					UnidadMedidaDTO um2 = new UnidadMedidaDTO();
					try {
						um2.setId(entity2.getInsumo().getUnidadMedida().getId());
						um2.setNombre(entity2.getInsumo().getUnidadMedida().getNombre());
						um2.setAbreviatura(entity2.getInsumo().getUnidadMedida().getAbreviatura());
						um2.setEliminado(entity2.getInsumo().getUnidadMedida().isEliminado());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					ingrediente.setUnidadMedida(um2);
					ingrediente.setEliminado(entity2.isEliminado());

					ingredientes.add(ingrediente);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			dto.setDetalle(ingredientes);

			try {
				PlatoCategoriaDTO platoCategoria = new PlatoCategoriaDTO();
				platoCategoria.setId(entity.getCategoria().getId());
				platoCategoria.setNombre(entity.getCategoria().getNombre());
				platoCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				dto.setCategoria(platoCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return dto;

	}

	@Transactional
	public boolean verificarStock(int id, int cantidad) {
		Boolean hayStock = true;
		try {
			for (DetallePlato detPlato : detallePlatoRepository.findAllPorPlato(id)) {
				Insumo insumo = new Insumo();
				if (!detPlato.getInsumo().getNombre().equals("Insumo Vacio") || detPlato.getInsumo() != null) {
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
						if ((insumo.getStockActual() - (detPlato.getCantidad() * cantidad)) < 0) {
							hayStock = false;
							break;
						}

					} else if ((insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("kg")
							&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("g"))
							|| (insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("l")
									&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("ml"))) {
						if (((insumo.getStockActual() * 1000) - (detPlato.getCantidad() * cantidad) / 1000) < 0) {
							hayStock = false;
							break;
						}

					} else if ((insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("g")
							&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("kg"))
							|| (insumo.getUnidadMedida().getAbreviatura().toLowerCase().equals("ml")
									&& detPlato.getUnidadMedida().getAbreviatura().toLowerCase().equals("l"))) {
						if ((insumo.getStockActual() - ((detPlato.getCantidad() / 1000) * cantidad) * 1000) < 0) {
							hayStock = false;
							break;
						}

					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return hayStock;
	}

	@Transactional
	public PlatoDTO save(PlatoDTO platoDTO) {

		Plato plato = new Plato();

		plato.setNombre(platoDTO.getNombre());
		plato.setDescripcion(platoDTO.getDescripcion());
		plato.setTiempoPreparacion(platoDTO.getTiempoPreparacion());
		plato.setPrecioVenta(platoDTO.getPrecioVenta());
		plato.setPrecioCosto(platoDTO.getPrecioCosto());
		plato.setImagen(platoDTO.getImagen());
		plato.setCantidadVendida(platoDTO.getCantidadVendida());
		plato.setEliminado(platoDTO.isEliminado());

		try {

			PlatoCategoria platoCategoria = new PlatoCategoria();
			platoCategoria.setId(platoDTO.getCategoria().getId());
			plato.setCategoria(platoCategoria);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		platoRepository.save(plato);

		platoDTO.setId(plato.getId());
		return platoDTO;

	}

	@Transactional
	public PlatoDTO update(PlatoDTO platoDTO, int id) {

		Optional<Plato> optional = platoRepository.findById(id);
		Plato plato = new Plato();

		try {

			plato = optional.get();

			plato.setNombre(platoDTO.getNombre());
			plato.setTiempoPreparacion(platoDTO.getTiempoPreparacion());
			plato.setPrecioVenta(platoDTO.getPrecioVenta());
			plato.setPrecioCosto(platoDTO.getPrecioCosto());
			plato.setImagen(platoDTO.getImagen());
			plato.setCantidadVendida(platoDTO.getCantidadVendida());
			plato.setEliminado(platoDTO.isEliminado());

			try {

				PlatoCategoria platoCategoria = new PlatoCategoria();
				platoCategoria.setId(platoDTO.getCategoria().getId());
				plato.setCategoria(platoCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			platoRepository.save(plato);

			platoDTO.setId(plato.getId());

		} catch (Exception e) {

			System.out.println("Bad Request");
			platoDTO.setId(0);

		}

		return platoDTO;

	}

	public boolean delete(int id) {
		try {
			platoRepository.deletePlatoById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
