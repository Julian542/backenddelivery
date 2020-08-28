package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.PlatoCategoriaDTO;
import com.utn.demo.entity.PlatoCategoria;
import com.utn.demo.repository.PlatoCategoriaRepository;

@Service
public class PlatoCategoriaService {

	private PlatoCategoriaRepository platoCategoriaRepository;

	public PlatoCategoriaService(PlatoCategoriaRepository platoCategoriaRepository) {
		this.platoCategoriaRepository = platoCategoriaRepository;
	}

	@Transactional
	public List<PlatoCategoriaDTO> getAll() {
		List<PlatoCategoriaDTO> result = new ArrayList<>();
		try {
			for (PlatoCategoria object2 : platoCategoriaRepository.findAllMod()) {
				PlatoCategoriaDTO object = new PlatoCategoriaDTO();
				object.setId(object2.getId());
				object.setImagen(object2.getImagen());
				object.setNombre(object2.getNombre());
				object.setDescripcion(object2.getDescripcion());
				object.setEliminado(object2.isEliminado());
				result.add(object);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public PlatoCategoriaDTO getOne(int id) {
		PlatoCategoriaDTO object = new PlatoCategoriaDTO();
		try {
			PlatoCategoria object2 = platoCategoriaRepository.findByIdMod(id);
			object.setId(object2.getId());
			object.setNombre(object2.getNombre());
			object.setImagen(object2.getImagen());
			object.setDescripcion(object2.getDescripcion());
			object.setEliminado(object2.isEliminado());
		} catch (Exception e) {
			System.out.println("No existe el id");
		}
		return object;
	}

	@Transactional
	public PlatoCategoriaDTO save(PlatoCategoriaDTO platoCategoriaDTO) {
		PlatoCategoria platoCategoria = new PlatoCategoria();
		try {
			platoCategoria.setNombre(platoCategoriaDTO.getNombre());
			platoCategoria.setImagen(platoCategoriaDTO.getImagen());
			platoCategoria.setDescripcion(platoCategoriaDTO.getDescripcion());
			platoCategoria.setEliminado(platoCategoriaDTO.isEliminado());
			platoCategoriaRepository.save(platoCategoria);
			platoCategoriaDTO.setId(platoCategoria.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return platoCategoriaDTO;
	}

	@Transactional
	public PlatoCategoriaDTO update(PlatoCategoriaDTO platoCategoriaDTO, int id) {
		Optional<PlatoCategoria> optional = platoCategoriaRepository.findById(id);
		PlatoCategoria platoCategoria = new PlatoCategoria();
		try {
			platoCategoria = optional.get();
			platoCategoria.setNombre(platoCategoriaDTO.getNombre());
			platoCategoria.setImagen(platoCategoriaDTO.getImagen());
			platoCategoria.setDescripcion(platoCategoriaDTO.getDescripcion());
			platoCategoria.setEliminado(platoCategoriaDTO.isEliminado());
			platoCategoriaRepository.save(platoCategoria);
			platoCategoriaDTO.setId(platoCategoria.getId());
		} catch (Exception e) {
			System.out.println("Bad Request");
			platoCategoriaDTO.setId(0);
		}
		return platoCategoriaDTO;
	}

	@Transactional
	public boolean delete(int id) {
		try {
			platoCategoriaRepository.deletePlatoCategoriaById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
