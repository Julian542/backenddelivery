package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.entity.UnidadMedida;
import com.utn.demo.repository.UnidadMedidaRepository;

@Service
public class UnidadMedidaService {

	private UnidadMedidaRepository unidadMedidaRepository;

	public UnidadMedidaService(UnidadMedidaRepository unidadMedidaRepository) {
		this.unidadMedidaRepository = unidadMedidaRepository;
	}

	@Transactional
	public List<UnidadMedidaDTO> getAll() {

		List<UnidadMedidaDTO> result = new ArrayList<>();

		for (UnidadMedida object2 : unidadMedidaRepository.findAll()) {
			UnidadMedidaDTO object = new UnidadMedidaDTO();
			object.setId(object2.getId());
			object.setNombre(object2.getNombre());
			object.setAbreviatura(object2.getAbreviatura());
			object.setEliminado(object2.isEliminado());

			result.add(object);
		}

		return result;

	}

	@Transactional
	public UnidadMedidaDTO getOne(int id) {

		Optional<UnidadMedida> aOptional = unidadMedidaRepository.findById(id);
		UnidadMedidaDTO object = new UnidadMedidaDTO();

		try {

			UnidadMedida object2 = aOptional.get();
			object.setId(object2.getId());
			object.setNombre(object2.getNombre());
			object.setAbreviatura(object2.getAbreviatura());
			object.setEliminado(object2.isEliminado());

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return object;

	}

	@Transactional
	public UnidadMedidaDTO save(UnidadMedidaDTO unidadMedidaDTO) {

		UnidadMedida unidadMedida = new UnidadMedida();

		unidadMedida.setNombre(unidadMedidaDTO.getNombre());
		unidadMedida.setAbreviatura(unidadMedidaDTO.getAbreviatura());
		unidadMedida.setEliminado(unidadMedidaDTO.isEliminado());

		unidadMedidaRepository.save(unidadMedida);

		unidadMedidaDTO.setId(unidadMedida.getId());
		return unidadMedidaDTO;

	}

	@Transactional
	public UnidadMedidaDTO update(UnidadMedidaDTO unidadMedidaDTO, int id) {

		Optional<UnidadMedida> optional = unidadMedidaRepository.findById(id);
		UnidadMedida unidadMedida = new UnidadMedida();

		try {

			unidadMedida = optional.get();

			unidadMedida.setNombre(unidadMedidaDTO.getNombre());
			unidadMedida.setAbreviatura(unidadMedidaDTO.getAbreviatura());
			unidadMedida.setEliminado(unidadMedidaDTO.isEliminado());

			unidadMedidaRepository.save(unidadMedida);

			unidadMedidaDTO.setId(unidadMedida.getId());

		} catch (Exception e) {

			System.out.println("Bad Request");
			unidadMedidaDTO.setId(0);

		}

		return unidadMedidaDTO;

	}

	public boolean delete(int id) {
		try {
			unidadMedidaRepository.deleteUnidadMedidaById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
