package com.utn.demo.service;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.DetallePlatoDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.entity.DetallePlato;
import com.utn.demo.entity.Insumo;
import com.utn.demo.repository.DetallePlatoRepository;

@Service
public class DetallePlatoService {

	private DetallePlatoRepository platoDetalleRepository;

	public DetallePlatoService(DetallePlatoRepository platoDetalleRepository) {
		this.platoDetalleRepository = platoDetalleRepository;
	}

	@Transactional
	public List<DetallePlatoDTO> getAll() {

		List<DetallePlatoDTO> result = new ArrayList<>();

		for (DetallePlato entity : platoDetalleRepository.findAll()) {

			DetallePlatoDTO dto = new DetallePlatoDTO();
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());
			dto.setEliminado(entity.isEliminado());

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
				insumo.setEliminado(entity.getInsumo().isEliminado());

				dto.setIngrediente(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	@Transactional
	public DetallePlatoDTO getOne(int id) {

		Optional<DetallePlato> aOptional = platoDetalleRepository.findById(id);
		DetallePlatoDTO dto = new DetallePlatoDTO();

		try {

			DetallePlato entity = aOptional.get();
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());
			dto.setEliminado(entity.isEliminado());

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
				insumo.setEliminado(entity.getInsumo().isEliminado());
				dto.setIngrediente(insumo);
				

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return dto;

	}

	@Transactional
	public DetallePlatoDTO save(DetallePlatoDTO platoDetalleDTO) {

		DetallePlato platoDetalle = new DetallePlato();

		platoDetalle.setCantidad(platoDetalleDTO.getCantidad());
		platoDetalle.setEliminado(platoDetalleDTO.isEliminado());

		try {

			Insumo insumo = new Insumo();
			insumo.setId(platoDetalleDTO.getIngrediente().getId());
			platoDetalle.setInsumo(insumo);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		platoDetalleRepository.save(platoDetalle);

		platoDetalleDTO.setId(platoDetalle.getId());

		return platoDetalleDTO;

	}

	@Transactional
	public DetallePlatoDTO update(DetallePlatoDTO platoDetalleDTO, int id) {

		Optional<DetallePlato> optional = platoDetalleRepository.findById(id);
		DetallePlato platoDetalle = new DetallePlato();

		try {

			platoDetalle = optional.get();

			platoDetalle.setCantidad(platoDetalleDTO.getCantidad());
			platoDetalle.setEliminado(platoDetalleDTO.isEliminado());

			try {
				Insumo articulo = new Insumo();
				articulo.setId(platoDetalleDTO.getIngrediente().getId());
				platoDetalle.setInsumo(articulo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			platoDetalleRepository.save(platoDetalle);

			platoDetalleDTO.setId(platoDetalle.getId());

		} catch (Exception e) {

			System.out.println("Bad Request");
			platoDetalleDTO.setId(0);

		}

		return platoDetalleDTO;

	}

	public boolean delete(int id) {
		try {
			platoDetalleRepository.deleteDetallePlatoById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
