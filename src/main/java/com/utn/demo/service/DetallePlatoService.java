package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.DetallePlatoDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.entity.DetallePlato;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.Plato;
import com.utn.demo.entity.UnidadMedida;
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

		for (DetallePlato entity : platoDetalleRepository.findAllMod()) {

			DetallePlatoDTO dto = new DetallePlatoDTO();
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());

			try {
				UnidadMedidaDTO unM = new UnidadMedidaDTO();
				unM.setId(entity.getUnidadMedida().getId());
				unM.setNombre(entity.getUnidadMedida().getNombre());
				unM.setAbreviatura(entity.getUnidadMedida().getAbreviatura());
				unM.setEliminado(entity.getUnidadMedida().isEliminado());

				dto.setUnidadMedida(unM);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

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

				UnidadMedidaDTO um = new UnidadMedidaDTO();
				um.setId(entity.getInsumo().getUnidadMedida().getId());
				um.setNombre(entity.getInsumo().getUnidadMedida().getNombre());
				um.setAbreviatura(entity.getInsumo().getUnidadMedida().getAbreviatura());
				um.setEliminado(entity.getInsumo().getUnidadMedida().isEliminado());

				insumo.setUnidadMedida(um);

				dto.setIngrediente(insumo);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(dto);
		}

		return result;

	}

	@Transactional
	public List<DetallePlatoDTO> getAllPorPlato(int id) {

		List<DetallePlatoDTO> result = new ArrayList<>();

		for (DetallePlato entity : platoDetalleRepository.findAllPorPlato(id)) {

			DetallePlatoDTO dto = new DetallePlatoDTO();
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());
			dto.setEliminado(entity.isEliminado());

			try {
				UnidadMedidaDTO unM = new UnidadMedidaDTO();
				unM.setId(entity.getUnidadMedida().getId());
				unM.setNombre(entity.getUnidadMedida().getNombre());
				unM.setAbreviatura(entity.getUnidadMedida().getAbreviatura());
				unM.setEliminado(entity.getUnidadMedida().isEliminado());

				dto.setUnidadMedida(unM);
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
				insumo.setEliminado(entity.getInsumo().isEliminado());

				UnidadMedidaDTO um = new UnidadMedidaDTO();
				um.setId(entity.getInsumo().getUnidadMedida().getId());
				um.setNombre(entity.getInsumo().getUnidadMedida().getNombre());
				um.setAbreviatura(entity.getInsumo().getUnidadMedida().getAbreviatura());
				um.setEliminado(entity.getInsumo().getUnidadMedida().isEliminado());

				insumo.setUnidadMedida(um);

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

		DetallePlatoDTO dto = new DetallePlatoDTO();

		try {

			DetallePlato entity = platoDetalleRepository.findByIdMod(id);
			dto.setId(entity.getId());
			dto.setCantidad(entity.getCantidad());
			dto.setEliminado(entity.isEliminado());

			try {
				UnidadMedidaDTO unM = new UnidadMedidaDTO();
				unM.setId(entity.getUnidadMedida().getId());
				unM.setNombre(entity.getUnidadMedida().getNombre());
				unM.setAbreviatura(entity.getUnidadMedida().getAbreviatura());
				unM.setEliminado(entity.getUnidadMedida().isEliminado());

				dto.setUnidadMedida(unM);
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
				insumo.setEliminado(entity.getInsumo().isEliminado());

				UnidadMedidaDTO um = new UnidadMedidaDTO();
				um.setId(entity.getInsumo().getUnidadMedida().getId());
				um.setNombre(entity.getInsumo().getUnidadMedida().getNombre());
				um.setAbreviatura(entity.getInsumo().getUnidadMedida().getAbreviatura());
				um.setEliminado(entity.getInsumo().getUnidadMedida().isEliminado());

				insumo.setUnidadMedida(um);

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

		try {

			platoDetalle.setCantidad(platoDetalleDTO.getCantidad());
			platoDetalle.setEliminado(platoDetalleDTO.isEliminado());
			Insumo insumo = new Insumo();
			insumo.setId(platoDetalleDTO.getIngrediente().getId());
			platoDetalle.setInsumo(insumo);
			UnidadMedida um = new UnidadMedida();
			um.setId(platoDetalleDTO.getUnidadMedida().getId());
			platoDetalle.setUnidadMedida(um);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			Plato plato = new Plato();
			plato.setId(platoDetalleDTO.getPlato().getId());
			platoDetalle.setPlato(plato);
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
				Insumo insumo = new Insumo();
				insumo.setId(platoDetalleDTO.getIngrediente().getId());
				platoDetalle.setInsumo(insumo);
				UnidadMedida um = new UnidadMedida();
				um.setId(platoDetalleDTO.getUnidadMedida().getId());
				platoDetalle.setUnidadMedida(um);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {
				Plato plato = new Plato();
				plato.setId(platoDetalleDTO.getPlato().getId());
				platoDetalle.setPlato(plato);
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

	@Transactional
	public boolean delete(int id) {
		try {
			platoDetalleRepository.deleteDetallePlatoById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
