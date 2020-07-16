package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.InsumoCategoriaDTO;
import com.utn.demo.entity.InsumoCategoria;
import com.utn.demo.repository.InsumoCategoriaRepository;

@Service
public class InsumoCategoriaService {

	private InsumoCategoriaRepository insumoCategoriaRepository;

	public InsumoCategoriaService(InsumoCategoriaRepository insumoCategoriaRepository) {
		this.insumoCategoriaRepository = insumoCategoriaRepository;
	}

	@Transactional
	public List<InsumoCategoriaDTO> getAll() {
		List<InsumoCategoriaDTO> result = new ArrayList<>();
		try {
			for (InsumoCategoria entity : insumoCategoriaRepository.findAll()) {
				InsumoCategoriaDTO dto = new InsumoCategoriaDTO();
				dto.setId(entity.getId());
				dto.setNombre(entity.getNombre());
				dto.setDescripcion(entity.getDescripcion());
				dto.setEliminado(entity.isEliminado());
				result.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public InsumoCategoriaDTO getOne(int id) {
		Optional<InsumoCategoria> aOptional = insumoCategoriaRepository.findById(id);
		InsumoCategoriaDTO dto = new InsumoCategoriaDTO();
		try {
			InsumoCategoria entity = aOptional.get();
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setDescripcion(entity.getDescripcion());
			dto.setEliminado(entity.isEliminado());
		} catch (Exception e) {
			System.out.println("No existe el id");
		}
		return dto;
	}

	@Transactional
	public InsumoCategoriaDTO save(InsumoCategoriaDTO insumoCategoriaDTO) {
		InsumoCategoria insumoCategoria = new InsumoCategoria();
		try {
			insumoCategoria.setNombre(insumoCategoriaDTO.getNombre());
			insumoCategoria.setDescripcion(insumoCategoriaDTO.getDescripcion());
			insumoCategoria.setEliminado(insumoCategoriaDTO.isEliminado());
			insumoCategoriaRepository.save(insumoCategoria);
			insumoCategoriaDTO.setId(insumoCategoria.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return insumoCategoriaDTO;
	}

	@Transactional
	public InsumoCategoriaDTO update(InsumoCategoriaDTO insumoCategoriaDTO, int id) {
		Optional<InsumoCategoria> optional = insumoCategoriaRepository.findById(id);
		InsumoCategoria insumoCategoria = new InsumoCategoria();
		try {
			insumoCategoria = optional.get();
			insumoCategoria.setNombre(insumoCategoriaDTO.getNombre());
			insumoCategoria.setDescripcion(insumoCategoriaDTO.getDescripcion());
			insumoCategoria.setEliminado(insumoCategoriaDTO.isEliminado());
			insumoCategoriaRepository.save(insumoCategoria);
			insumoCategoriaDTO.setId(insumoCategoria.getId());
		} catch (Exception e) {
			System.out.println("Bad Request");
			insumoCategoriaDTO.setId(0);
		}
		return insumoCategoriaDTO;
	}

	@Transactional
	public boolean delete(int id) {
		try {
			insumoCategoriaRepository.deleteInsumoCategoriaById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
