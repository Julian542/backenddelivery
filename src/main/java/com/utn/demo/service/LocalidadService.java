package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.LocalidadDTO;
import com.utn.demo.entity.Localidad;
import com.utn.demo.repository.LocalidadRepository;

@Service
public class LocalidadService {

	protected final LocalidadRepository repository;

	public LocalidadService(LocalidadRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public List<LocalidadDTO> findAll() {
		List<LocalidadDTO> dtos = new ArrayList<>();
		try {
			List<Localidad> entidades = repository.findAllMod();
			for (Localidad l : entidades) {
				LocalidadDTO unDto = new LocalidadDTO();
				unDto.setId(l.getId());
				unDto.setNombre(l.getNombre());
				unDto.setEliminado(l.isEliminado());
				dtos.add(unDto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dtos;
	}

	@Transactional
	public LocalidadDTO findById(int id) {
		LocalidadDTO dto = new LocalidadDTO();
		try {
			Localidad entidad = repository.findByIdMod(id);
			dto.setId(entidad.getId());
			dto.setNombre(entidad.getNombre());
			dto.setEliminado(entidad.isEliminado());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}

	@Transactional
	public LocalidadDTO save(LocalidadDTO dto) {
		Localidad entity = new Localidad();
		try {
			entity.setNombre(dto.getNombre());
			entity.setEliminado(dto.isEliminado());
			entity = repository.save(entity);
			dto.setId(entity.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}

	@Transactional
	public LocalidadDTO update(int id, LocalidadDTO dto) {
		try {
			Optional<Localidad> entityOptional = repository.findById(id);
			Localidad entidad = entityOptional.get();
			entidad.setId(dto.getId());
			entidad.setNombre(dto.getNombre());
			entidad.setEliminado(dto.isEliminado());
			repository.save(entidad);
			dto.setId(entidad.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}

	@Transactional
	public boolean delete(int id) {
		try {
			repository.deleteLocalidadById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
