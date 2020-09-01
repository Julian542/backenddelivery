package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.InsumoCategoriaDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.InsumoCategoria;
import com.utn.demo.entity.UnidadMedida;
import com.utn.demo.repository.InsumoRepository;

@Service
public class InsumoService {

	private InsumoRepository insumoRepository;

	public InsumoService(InsumoRepository insumoRepository) {
		this.insumoRepository = insumoRepository;
	}

	public List<InsumoDTO> getAll() {

		List<InsumoDTO> result = new ArrayList<>();

		for (Insumo entity : insumoRepository.findAllMod()) {

			InsumoDTO dto = new InsumoDTO();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCompra(entity.getPrecioCompra());
			dto.setStockActual(entity.getStockActual());
			dto.setStockMinimo(entity.getStockMinimo());
			dto.setStockMaximo(entity.getStockMaximo());
			dto.setImagen(entity.getImagen());
			dto.setEsInsumo(entity.isEsInsumo());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setEliminado(entity.isEliminado());

			try {

				InsumoCategoriaDTO articuloCategoria = new InsumoCategoriaDTO();
				articuloCategoria.setId(entity.getCategoria().getId());
				articuloCategoria.setNombre(entity.getCategoria().getNombre());
				articuloCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				articuloCategoria.setEliminado(entity.getCategoria().isEliminado());
				dto.setCategoria(articuloCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
				unidadMedida.setId(entity.getUnidadMedida().getId());
				unidadMedida.setNombre(entity.getUnidadMedida().getNombre());
				unidadMedida.setAbreviatura(entity.getUnidadMedida().getAbreviatura());
				unidadMedida.setEliminado(entity.getUnidadMedida().isEliminado());
				dto.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	public List<InsumoDTO> getAllporCategoria(int id) {

		List<InsumoDTO> result = new ArrayList<>();

		for (Insumo entity : insumoRepository.getAllporCategoria(id)) {

			InsumoDTO dto = new InsumoDTO();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCompra(entity.getPrecioCompra());
			dto.setStockActual(entity.getStockActual());
			dto.setStockMinimo(entity.getStockMinimo());
			dto.setStockMaximo(entity.getStockMaximo());
			dto.setImagen(entity.getImagen());
			dto.setEsInsumo(entity.isEsInsumo());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setEliminado(entity.isEliminado());

			try {

				InsumoCategoriaDTO articuloCategoria = new InsumoCategoriaDTO();
				articuloCategoria.setId(entity.getCategoria().getId());
				articuloCategoria.setNombre(entity.getCategoria().getNombre());
				articuloCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				articuloCategoria.setEliminado(entity.getCategoria().isEliminado());
				dto.setCategoria(articuloCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
				unidadMedida.setId(entity.getUnidadMedida().getId());
				unidadMedida.setNombre(entity.getUnidadMedida().getNombre());
				unidadMedida.setAbreviatura(entity.getUnidadMedida().getAbreviatura());
				unidadMedida.setEliminado(entity.getUnidadMedida().isEliminado());
				dto.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	public List<InsumoDTO> buscarPorCategoriaNoInsumo(int id) {

		List<InsumoDTO> result = new ArrayList<>();

		for (Insumo entity : insumoRepository.buscarPorCategoriaNoInsumo(id)) {

			InsumoDTO dto = new InsumoDTO();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCompra(entity.getPrecioCompra());
			dto.setStockActual(entity.getStockActual());
			dto.setStockMinimo(entity.getStockMinimo());
			dto.setStockMaximo(entity.getStockMaximo());
			dto.setImagen(entity.getImagen());
			dto.setEsInsumo(entity.isEsInsumo());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setEliminado(entity.isEliminado());

			try {

				InsumoCategoriaDTO articuloCategoria = new InsumoCategoriaDTO();
				articuloCategoria.setId(entity.getCategoria().getId());
				articuloCategoria.setNombre(entity.getCategoria().getNombre());
				articuloCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				articuloCategoria.setEliminado(entity.getCategoria().isEliminado());
				dto.setCategoria(articuloCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
				unidadMedida.setId(entity.getUnidadMedida().getId());
				unidadMedida.setNombre(entity.getUnidadMedida().getNombre());
				unidadMedida.setAbreviatura(entity.getUnidadMedida().getAbreviatura());
				unidadMedida.setEliminado(entity.getUnidadMedida().isEliminado());
				dto.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	public List<InsumoDTO> getAllNoInsumos(boolean esInsumo) {

		List<InsumoDTO> result = new ArrayList<>();

		for (Insumo entity : insumoRepository.getAllNoInsumos(esInsumo)) {

			InsumoDTO dto = new InsumoDTO();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCompra(entity.getPrecioCompra());
			dto.setStockActual(entity.getStockActual());
			dto.setStockMinimo(entity.getStockMinimo());
			dto.setStockMaximo(entity.getStockMaximo());
			dto.setImagen(entity.getImagen());
			dto.setEsInsumo(entity.isEsInsumo());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setEliminado(entity.isEliminado());

			try {
				InsumoCategoriaDTO articuloCategoria = new InsumoCategoriaDTO();
				articuloCategoria.setId(entity.getCategoria().getId());
				articuloCategoria.setNombre(entity.getCategoria().getNombre());
				articuloCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				articuloCategoria.setEliminado(entity.getCategoria().isEliminado());
				dto.setCategoria(articuloCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
				unidadMedida.setId(entity.getUnidadMedida().getId());
				unidadMedida.setNombre(entity.getUnidadMedida().getNombre());
				unidadMedida.setAbreviatura(entity.getUnidadMedida().getAbreviatura());
				unidadMedida.setEliminado(entity.getUnidadMedida().isEliminado());
				dto.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	public InsumoDTO getOne(int id) {

		InsumoDTO dto = new InsumoDTO();

		try {

			Insumo entity = insumoRepository.findByIdMod(id);
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCompra(entity.getPrecioCompra());
			dto.setStockActual(entity.getStockActual());
			dto.setStockMinimo(entity.getStockMinimo());
			dto.setStockMaximo(entity.getStockMaximo());
			dto.setImagen(entity.getImagen());
			dto.setEsInsumo(entity.isEsInsumo());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setEliminado(entity.isEliminado());

			try {

				InsumoCategoriaDTO articuloCategoria = new InsumoCategoriaDTO();
				articuloCategoria.setId(entity.getCategoria().getId());
				articuloCategoria.setNombre(entity.getCategoria().getNombre());
				articuloCategoria.setDescripcion(entity.getCategoria().getDescripcion());
				articuloCategoria.setEliminado(entity.getCategoria().isEliminado());
				dto.setCategoria(articuloCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
				unidadMedida.setId(entity.getUnidadMedida().getId());
				unidadMedida.setNombre(entity.getUnidadMedida().getNombre());
				unidadMedida.setAbreviatura(entity.getUnidadMedida().getAbreviatura());
				unidadMedida.setEliminado(entity.getUnidadMedida().isEliminado());
				dto.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return dto;

	}

	public InsumoDTO save(InsumoDTO insumoDTO) {

		Insumo insumo = new Insumo();

		insumo.setNombre(insumoDTO.getNombre());
		insumo.setDescripcion(insumoDTO.getDescripcion());
		insumo.setPrecioCompra(insumoDTO.getPrecioCompra());
		insumo.setStockActual(insumoDTO.getStockActual());
		insumo.setStockMinimo(insumoDTO.getStockMinimo());
		insumo.setStockMaximo(insumoDTO.getStockMaximo());
		insumo.setImagen(insumoDTO.getImagen());
		insumo.setEsInsumo(insumoDTO.isEsInsumo());
		insumo.setPrecioVenta(insumoDTO.getPrecioVenta());
		insumo.setEliminado(insumoDTO.isEliminado());
		// Agregar imagen

		try {
			InsumoCategoria insumoCategoria = new InsumoCategoria();
			insumoCategoria.setId(insumoDTO.getCategoria().getId());
			insumo.setCategoria(insumoCategoria);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {

			UnidadMedida unidadMedida = new UnidadMedida();
			unidadMedida.setId(insumoDTO.getUnidadMedida().getId());
			insumo.setUnidadMedida(unidadMedida);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		insumoRepository.save(insumo);

		insumoDTO.setId(insumo.getId());
		return insumoDTO;

	}

	public InsumoDTO update(InsumoDTO insumoDTO, int id) {

		Optional<Insumo> optional = insumoRepository.findById(id);
		Insumo insumo = new Insumo();

		try {

			insumo = optional.get();

			insumo.setNombre(insumoDTO.getNombre());
			insumo.setDescripcion(insumoDTO.getDescripcion());
			insumo.setPrecioCompra(insumoDTO.getPrecioCompra());
			insumo.setPrecioVenta(insumoDTO.getPrecioVenta());
			insumo.setStockActual(insumoDTO.getStockActual());
			insumo.setStockMinimo(insumoDTO.getStockMinimo());
			insumo.setStockMaximo(insumoDTO.getStockMaximo());
			insumo.setImagen(insumoDTO.getImagen());
			insumo.setEsInsumo(insumoDTO.isEsInsumo());
			insumo.setEliminado(insumoDTO.isEliminado());

			try {

				InsumoCategoria articuloCategoria = new InsumoCategoria();
				articuloCategoria.setId(insumoDTO.getCategoria().getId());
				insumo.setCategoria(articuloCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				UnidadMedida unidadMedida = new UnidadMedida();
				unidadMedida.setId(insumoDTO.getUnidadMedida().getId());
				insumo.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			insumoRepository.save(insumo);

			insumoDTO.setId(insumo.getId());

		} catch (Exception e) {

			System.out.println("Bad Request");
			insumoDTO.setId(0);

		}

		return insumoDTO;

	}

	public boolean delete(int id) {
		try {
			insumoRepository.deleteInsumoById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<InsumoDTO> getStocks(int id) {
		List<InsumoDTO> InsumoDTOs = new ArrayList<>();
		try {
			for (Insumo insumo : insumoRepository.getInsumosWithLowStock(id)) {
				InsumoDTO insumoDTO = new InsumoDTO();

				insumoDTO.setDescripcion(insumo.getDescripcion());
				insumoDTO.setEsInsumo(insumo.isEsInsumo());
				insumoDTO.setId(insumo.getId());
				insumoDTO.setImagen(insumo.getImagen());
				insumoDTO.setNombre(insumo.getNombre());
				insumoDTO.setPrecioCompra(insumo.getPrecioCompra());
				insumoDTO.setPrecioVenta(insumo.getPrecioVenta());
				insumoDTO.setStockActual(insumo.getStockActual());
				insumoDTO.setStockMaximo(insumo.getStockMaximo());
				insumoDTO.setStockMinimo(insumo.getStockMinimo());
				insumoDTO.setEliminado(insumo.isEliminado());

				UnidadMedidaDTO unidadMedidaDTO = new UnidadMedidaDTO();
				unidadMedidaDTO.setId(insumo.getUnidadMedida().getId());
				unidadMedidaDTO.setNombre(insumo.getUnidadMedida().getNombre());
				insumoDTO.setUnidadMedida(unidadMedidaDTO);

				InsumoCategoriaDTO insumoCategoria = new InsumoCategoriaDTO();
				insumoCategoria.setId(insumo.getCategoria().getId());
				insumoDTO.setCategoria(insumoCategoria);

				InsumoDTOs.add(insumoDTO);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return InsumoDTOs;
	}
	
	public List<InsumoDTO> getStocksSinCategoria() {
		List<InsumoDTO> InsumoDTOs = new ArrayList<>();
		try {
			for (Insumo insumo : insumoRepository.getInsumosWithLowStockSinCategoria()) {
				InsumoDTO insumoDTO = new InsumoDTO();

				insumoDTO.setDescripcion(insumo.getDescripcion());
				insumoDTO.setEsInsumo(insumo.isEsInsumo());
				insumoDTO.setId(insumo.getId());
				insumoDTO.setImagen(insumo.getImagen());
				insumoDTO.setNombre(insumo.getNombre());
				insumoDTO.setPrecioCompra(insumo.getPrecioCompra());
				insumoDTO.setPrecioVenta(insumo.getPrecioVenta());
				insumoDTO.setStockActual(insumo.getStockActual());
				insumoDTO.setStockMaximo(insumo.getStockMaximo());
				insumoDTO.setStockMinimo(insumo.getStockMinimo());
				insumoDTO.setEliminado(insumo.isEliminado());

				UnidadMedidaDTO unidadMedidaDTO = new UnidadMedidaDTO();
				unidadMedidaDTO.setId(insumo.getUnidadMedida().getId());
				unidadMedidaDTO.setNombre(insumo.getUnidadMedida().getNombre());
				insumoDTO.setUnidadMedida(unidadMedidaDTO);

				InsumoCategoriaDTO insumoCategoria = new InsumoCategoriaDTO();
				insumoCategoria.setId(insumo.getCategoria().getId());
				insumoDTO.setCategoria(insumoCategoria);

				InsumoDTOs.add(insumoDTO);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return InsumoDTOs;
	}
}
