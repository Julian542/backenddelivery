package com.utn.demo.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.utn.demo.dtos.DetallePlatoDTO;
import com.utn.demo.dtos.InsumoCategoriaDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.PlatoCategoriaDTO;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.entity.DetallePlato;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.Plato;
import com.utn.demo.entity.PlatoCategoria;
import com.utn.demo.repository.PlatoRepository;

@Service
public class PlatoService {

	private PlatoRepository platoRepository;

	public PlatoService(PlatoRepository platoRepository) {
		this.platoRepository = platoRepository;
	}

	@Transactional
	public List<PlatoDTO> getAll() {

		List<PlatoDTO> result = new ArrayList<>();

		for (Plato entity : platoRepository.findAll()) {

			PlatoDTO dto = new PlatoDTO();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setTiempoPreparacion(entity.getTiempoPreparacion());
			dto.setImagen(entity.getImagen());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCosto(entity.getPrecioCosto());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setCantidadVendida(entity.getCantidadVendida());

			try {
				List<DetallePlatoDTO> platoDetalle = new ArrayList<>();

				for (DetallePlato platoDetalleInternal : entity.getDetalle()) {
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

					InsumoCategoriaDTO insumoCategoria = new InsumoCategoriaDTO();
					insumoCategoria.setId(platoDetalleInternal.getInsumo().getCategoria().getId());
					insumoCategoria.setNombre(platoDetalleInternal.getInsumo().getCategoria().getNombre());
					insumoCategoria.setDescripcion(platoDetalleInternal.getInsumo().getCategoria().getDescripcion());
					insumo.setCategoria(insumoCategoria);

					UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
					unidadMedida.setId(platoDetalleInternal.getInsumo().getUnidadMedida().getId());
					unidadMedida.setNombre(platoDetalleInternal.getInsumo().getUnidadMedida().getNombre());
					unidadMedida.setAbreviatura(platoDetalleInternal.getInsumo().getUnidadMedida().getAbreviatura());
					insumo.setUnidadMedida(unidadMedida);

					platoDetalleDTO.setIngrediente(insumo);

					platoDetalle.add(platoDetalleDTO);
				}
				dto.setDetalle(platoDetalle);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				PlatoCategoriaDTO platoCategoria = new PlatoCategoriaDTO();
				platoCategoria.setId(entity.getCategoria().getId());
				platoCategoria.setNombre(entity.getCategoria().getNombre());
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

		Optional<Plato> aOptional = platoRepository.findById(id);
		PlatoDTO dto = new PlatoDTO();

		try {

			Plato entity = aOptional.get();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setTiempoPreparacion(entity.getTiempoPreparacion());
			dto.setImagen(entity.getImagen());
			dto.setDescripcion(entity.getDescripcion());
			dto.setPrecioCosto(entity.getPrecioCosto());
			dto.setPrecioVenta(entity.getPrecioVenta());
			dto.setCantidadVendida(entity.getCantidadVendida());

			try {
				List<DetallePlatoDTO> platoDetalle = new ArrayList<>();

				for (DetallePlato platoDetalleInternal : entity.getDetalle()) {
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

					InsumoCategoriaDTO insumoCategoria = new InsumoCategoriaDTO();
					insumoCategoria.setId(platoDetalleInternal.getInsumo().getCategoria().getId());
					insumoCategoria.setNombre(platoDetalleInternal.getInsumo().getCategoria().getNombre());
					insumoCategoria.setDescripcion(platoDetalleInternal.getInsumo().getCategoria().getDescripcion());
					insumo.setCategoria(insumoCategoria);

					UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
					unidadMedida.setId(platoDetalleInternal.getInsumo().getUnidadMedida().getId());
					unidadMedida.setNombre(platoDetalleInternal.getInsumo().getUnidadMedida().getNombre());
					unidadMedida.setAbreviatura(platoDetalleInternal.getInsumo().getUnidadMedida().getAbreviatura());

					insumo.setUnidadMedida(unidadMedida);

					platoDetalleDTO.setIngrediente(insumo);

					platoDetalle.add(platoDetalleDTO);
				}
				dto.setDetalle(platoDetalle);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				PlatoCategoriaDTO platoCategoria = new PlatoCategoriaDTO();
				platoCategoria.setId(entity.getCategoria().getId());
				platoCategoria.setNombre(entity.getCategoria().getNombre());
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
	public PlatoDTO save(PlatoDTO platoDTO) {

		Plato plato = new Plato();

		plato.setNombre(platoDTO.getNombre());
		plato.setDescripcion(platoDTO.getDescripcion());
		plato.setTiempoPreparacion(platoDTO.getTiempoPreparacion());
		plato.setPrecioVenta(platoDTO.getPrecioVenta());
		plato.setPrecioCosto(platoDTO.getPrecioCosto());
		plato.setImagen(platoDTO.getImagen());
		plato.setCantidadVendida(platoDTO.getCantidadVendida());

		try {

			List<DetallePlato> platoDetalle = new ArrayList<>();
			for (DetallePlatoDTO platoDetalleDTO : platoDTO.getDetalle()) {
				DetallePlato platoDetalleTemp = new DetallePlato();
				platoDetalleTemp.setCantidad(platoDetalleDTO.getCantidad());

				Insumo articulo = new Insumo();
				articulo.setId(platoDetalleDTO.getIngrediente().getId());
				platoDetalleTemp.setInsumo(articulo);

				platoDetalle.add(platoDetalleTemp);
			}
			plato.setDetalle(platoDetalle);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

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

			try {
				List<DetallePlato> platoDetalle = new ArrayList<>();

				for (DetallePlatoDTO platoDetalleDTO : platoDTO.getDetalle()) {
					DetallePlato platoDetalleTemp = new DetallePlato();
					platoDetalleTemp.setId(platoDetalleDTO.getId());
					platoDetalleTemp.setCantidad(platoDetalleDTO.getCantidad());

					Insumo articulo = new Insumo();
					articulo.setId(platoDetalleDTO.getIngrediente().getId());
					platoDetalleTemp.setInsumo(articulo);

					platoDetalle.add(platoDetalleTemp);
				}
				plato.setDetalle(platoDetalle);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

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
		}catch(Exception e) {
			return false;
		}
	}

	@Transactional
	public List<PlatoDTO> getPlatosPopulares(Date fechaDesde, Date fechaHasta) throws Exception{
		List<PlatoDTO> platoDTOs = new ArrayList<>();
		try {
			List<Plato> platos = platoRepository.platosPopulares(fechaDesde, fechaHasta);
			for (Plato plato : platos) {
				PlatoDTO platosDTO = new PlatoDTO();
				platosDTO.setCantidadVendida(plato.getCantidadVendida());
				
				PlatoCategoriaDTO platoCategoriaDTO = new PlatoCategoriaDTO();
				platoCategoriaDTO.setId(plato.getCategoria().getId());
				
				platosDTO.setCategoria(platoCategoriaDTO);
				platosDTO.setDescripcion(plato.getDescripcion());
				
				List<DetallePlatoDTO> detallePlatoDTOs = new ArrayList<>();
				for (DetallePlato detallePlato : plato.getDetalle()) {
					DetallePlatoDTO detallePlatoDTO = new DetallePlatoDTO();
					detallePlatoDTO.setId(detallePlato.getId());
					detallePlatoDTOs.add(detallePlatoDTO);
				}
				
				platosDTO.setDetalle(detallePlatoDTOs);
				platosDTO.setId(plato.getId());
				platosDTO.setImagen(plato.getImagen());
				platosDTO.setNombre(plato.getNombre());
				platosDTO.setPrecioCosto(plato.getPrecioCosto());
				platosDTO.setPrecioVenta(plato.getPrecioVenta());
				platosDTO.setTiempoPreparacion(plato.getTiempoPreparacion());
				platoDTOs.add(platosDTO);
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return platoDTOs;
	}
}
