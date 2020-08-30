package com.utn.demo.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.Configuracion_EmpresaDTO;
import com.utn.demo.entity.Configuracion_Empresa;
import com.utn.demo.repository.Configuracion_EmpresaRepository;

@Service
public class Configuracion_EmpresaService {

	private Configuracion_EmpresaRepository configuracion_EmpresaRepository;

	public Configuracion_EmpresaService(Configuracion_EmpresaRepository configuracion_EmpresaRepository) {
		this.configuracion_EmpresaRepository = configuracion_EmpresaRepository;
	}

	@Transactional
	public Configuracion_EmpresaDTO getOne() throws Exception {
		List<Configuracion_Empresa> entity = configuracion_EmpresaRepository.findAllMod();
		Configuracion_EmpresaDTO cDto = new Configuracion_EmpresaDTO();
		try {
			for (Configuracion_Empresa i : entity) {
				cDto.setId(i.getId());
				cDto.setCantidadCocineros(i.getCantidadCocineros());
				cDto.setNombre(i.getNombre());
				cDto.setEmail(i.getEmail());
				cDto.setCuit(i.getCuit());
				cDto.setTelefono(i.getTelefono());
				cDto.setNumeroFiscal(i.getNumeroFiscal());
				cDto.setSociedad(i.getSociedad());
				cDto.setEliminado(i.isEliminado());
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return cDto;
	}

	@Transactional
	public Configuracion_EmpresaDTO save(Configuracion_EmpresaDTO dto) throws Exception {
		Configuracion_Empresa entity = new Configuracion_Empresa();
		try {
			entity.setCantidadCocineros(dto.getCantidadCocineros());
			entity.setNombre(dto.getNombre());
			entity.setEmail(dto.getEmail());
			entity.setCuit(dto.getCuit());
			entity.setNumeroFiscal(dto.getNumeroFiscal());
			entity.setTelefono(dto.getTelefono());
			entity.setSociedad(dto.getSociedad());
			entity.setEliminado(dto.isEliminado());
			entity = configuracion_EmpresaRepository.save(entity);
			dto.setId(entity.getId());

		} catch (Exception e) {
			throw new Exception();
		}
		return dto;
	}

	@Transactional
	public Configuracion_EmpresaDTO update(int id, Configuracion_EmpresaDTO DTO) throws Exception {

		try {
			Configuracion_Empresa entity = configuracion_EmpresaRepository.findByIdMod(id);
			entity.setId(DTO.getId());
			entity.setCantidadCocineros(DTO.getCantidadCocineros());
			entity.setNombre(DTO.getNombre());
			entity.setEmail(DTO.getEmail());
			entity.setCuit(DTO.getCuit());
			entity.setNumeroFiscal(DTO.getNumeroFiscal());
			entity.setSociedad(DTO.getSociedad());
			entity.setTelefono(DTO.getTelefono());
			entity.setEliminado(DTO.isEliminado());
			configuracion_EmpresaRepository.save(entity);
		} catch (Exception e) {
			throw new Exception();
		}
		return DTO;
	}

	@Transactional
	public boolean delete(int id) {
		try {
			configuracion_EmpresaRepository.deleteConfiguracionById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
