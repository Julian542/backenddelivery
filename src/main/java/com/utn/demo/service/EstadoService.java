package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.EstadoDTO;
import com.utn.demo.entity.Estado;
import com.utn.demo.repository.EstadoRepository;

@Service
public class EstadoService {

	private EstadoRepository estadoRepository;

	public EstadoService(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}

	@Transactional
	public List<EstadoDTO> getAll() {
		List<EstadoDTO> result = new ArrayList<>();
		try {
			for (Estado object2 : estadoRepository.findAllMod()) {
				EstadoDTO object = new EstadoDTO();
				object.setId(object2.getId());
				object.setNombre(object2.getNombre());
				object.setEliminado(object2.isEliminado());
				result.add(object);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Transactional
	public EstadoDTO getOne(int id) {

		EstadoDTO object = new EstadoDTO();
		try {
			Estado object2 = estadoRepository.findByIdMod(id);
			object.setId(object2.getId());
			object.setNombre(object2.getNombre());
			object.setEliminado(object2.isEliminado());
		} catch (Exception e) {
			System.out.println("No existe el id");
		}
		return object;
	}

	@Transactional
	public EstadoDTO save(EstadoDTO estadoDTO) {
		Estado estado = new Estado();
		try {
			estado.setNombre(estadoDTO.getNombre());
			estado.setEliminado(estadoDTO.isEliminado());
			estadoRepository.save(estado);
			estadoDTO.setId(estado.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return estadoDTO;
	}

	@Transactional
	public EstadoDTO update(int id, EstadoDTO estadoDTO) {

		Estado estado = new Estado();
		try {
			estado = estadoRepository.findByIdMod(id);
			estado.setNombre(estadoDTO.getNombre());
			estado.setEliminado(estadoDTO.isEliminado());
			estadoRepository.save(estado);
			estadoDTO.setId(estado.getId());
		} catch (Exception e) {
			System.out.println("Bad Request");
			estadoDTO.setId(0);
		}
		return estadoDTO;
	}

	@Transactional
	public boolean delete(int id) {
		try {
			estadoRepository.deleteEstadoById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
