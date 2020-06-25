package com.utn.demo.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.utn.demo.dtos.ControlStockDTO;
import com.utn.demo.entity.ControlStock;
import com.utn.demo.repository.ControlStockRepository;

@Service
public class ControlStockService {

	private ControlStockRepository controlStockRepository;

	public ControlStockService(ControlStockRepository controlStockRepository) {
		this.controlStockRepository = controlStockRepository;
	}

	public List<ControlStockDTO> getAll() throws Exception {
		List<ControlStock> entity = controlStockRepository.buscarAll();
		List<ControlStockDTO> cDto = new ArrayList<>();
		try {
			for (ControlStock i : entity) {
				ControlStockDTO dto = new ControlStockDTO();
				dto.setId(i.getId());
				dto.setNombreInsumo(i.getNombreInsumo());
				dto.setStockActual(i.getStockActual());
				dto.setStockMinimo(i.getStockMinimo());
				cDto.add(dto);
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return cDto;
	}

	@Transactional
	public ControlStockDTO save(ControlStockDTO DTO) throws Exception {
		ControlStock entity = new ControlStock();
		entity.setNombreInsumo(DTO.getNombreInsumo());
		entity.setStockActual(DTO.getStockActual());
		entity.setStockMinimo(DTO.getStockMinimo());
		try {
			entity = controlStockRepository.save(entity);
			DTO.setId(entity.getId());
			return DTO;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Transactional
	public ControlStockDTO update(long id, ControlStockDTO DTO) throws Exception {
		Optional<ControlStock> entityOptional = controlStockRepository.findById(id);
		try {
			ControlStock entity = entityOptional.get();
			entity.setId(id);
			entity.setNombreInsumo(DTO.getNombreInsumo());
			entity.setStockActual(DTO.getStockActual());
			entity.setStockMinimo(DTO.getStockMinimo());
			controlStockRepository.save(entity);
		} catch (Exception e) {
			throw new Exception();
		}
		return DTO;
	}

	@Transactional
	public boolean delete(long id) throws Exception {
		try {
			if (controlStockRepository.existsById(id)) {
				controlStockRepository.deleteById(id);
			} else {
				throw new Exception();
			}
			return true;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
