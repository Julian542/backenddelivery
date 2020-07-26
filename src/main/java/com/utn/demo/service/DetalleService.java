package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.DetalleDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.entity.Detalle;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.Plato;
import com.utn.demo.repository.DetalleRepository;

@Service
public class DetalleService {

	private DetalleRepository detalleRepository;

	public DetalleService(DetalleRepository detalleRepository) {
		this.detalleRepository = detalleRepository;
	}

	@Transactional
	public List<DetalleDTO> getAll() {

		List<DetalleDTO> result = new ArrayList<>();

		for (Detalle entity : detalleRepository.findAllMod()) {
			DetalleDTO dto = new DetalleDTO();
			dto.setId(entity.getId());
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

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return dto;

	}

	@Transactional
	public DetalleDTO save(DetalleDTO detalleDTO) {

		Detalle detalle = new Detalle();

		detalle.setCantidad(detalleDTO.getCantidad());
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

			result.add(dto);
		}

		return result;

	}
	
	
	public boolean delete(int id) {
		try {
			detalleRepository.deleteDetalleById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
