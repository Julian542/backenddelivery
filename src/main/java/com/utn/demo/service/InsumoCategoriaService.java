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
			for (InsumoCategoria entity : insumoCategoriaRepository.findAllMod()) {
				InsumoCategoriaDTO dto = new InsumoCategoriaDTO();
				dto.setId(entity.getId());
				dto.setNombre(entity.getNombre());
				dto.setDescripcion(entity.getDescripcion());
				dto.setEliminado(entity.isEliminado());
				dto.setEs_insumo(entity.isEs_insumo());
				result.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public List<InsumoCategoriaDTO> getAllNoInsumo() {
		List<InsumoCategoriaDTO> result = new ArrayList<>();
		try {
			for (InsumoCategoria entity : insumoCategoriaRepository.findAllNoInsumo()) {
				InsumoCategoriaDTO dto = new InsumoCategoriaDTO();
				dto.setId(entity.getId());
				dto.setNombre(entity.getNombre());
				dto.setDescripcion(entity.getDescripcion());
				dto.setEliminado(entity.isEliminado());
				dto.setEs_insumo(entity.isEs_insumo());
				result.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public InsumoCategoriaDTO getOne(int id) {
		InsumoCategoriaDTO dto = new InsumoCategoriaDTO();
		try {
			InsumoCategoria entity = insumoCategoriaRepository.findByIdMod(id);
			dto.setId(entity.getId());
			dto.setNombre(entity.getNombre());
			dto.setDescripcion(entity.getDescripcion());
			dto.setEliminado(entity.isEliminado());
			dto.setEs_insumo(entity.isEs_insumo());
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
			insumoCategoria.setEs_insumo(insumoCategoriaDTO.isEs_insumo());
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
			insumoCategoria.setEs_insumo(insumoCategoriaDTO.isEs_insumo());
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
