package com.utn.demo.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.ConfiguracionDTO;
import com.utn.demo.entity.Configuracion;
import com.utn.demo.repository.ConfiguracionRepository;

@Service
public class ConfiguracionService {

	private ConfiguracionRepository configuracionRepository;

	public ConfiguracionService(ConfiguracionRepository configuracionRepository) {
		this.configuracionRepository = configuracionRepository;
	}

	public ConfiguracionDTO getConfiguracion() throws Exception {
		List<Configuracion> entity = configuracionRepository.findAll();
		ConfiguracionDTO cDto = new ConfiguracionDTO();
		try {
			for (Configuracion i : entity) {
				cDto.setId(i.getId());
				cDto.setHorarioCierre(i.getHorarioCierre());
				cDto.setHorarioApertura(i.getHorarioApertura());
				cDto.setCantidadCocineros(i.getCantidadCocineros());
				cDto.setNombreEmpresa(i.getNombreEmpresa());
				cDto.setEmailEmpresa(i.getEmailEmpresa());
				cDto.setCuit(i.getCuit());
				cDto.setNumeroFiscal(i.getNumeroFiscal());
				cDto.setSociedad(i.getSociedad());
				cDto.setPaginaWeb(i.getPaginaWeb());
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return cDto;
	}
	
	@Transactional
	public ConfiguracionDTO save(ConfiguracionDTO dto) throws Exception{
		Configuracion entity = new Configuracion();
		entity.setHorarioCierre(dto.getHorarioCierre());
		entity.setHorarioApertura(dto.getHorarioApertura());
		entity.setCantidadCocineros(dto.getCantidadCocineros());
		entity.setNombreEmpresa(dto.getNombreEmpresa());
		entity.setEmailEmpresa(dto.getEmailEmpresa());
		entity.setCuit(dto.getCuit());
		entity.setNumeroFiscal(dto.getNumeroFiscal());
		entity.setSociedad(dto.getSociedad());
		entity.setPaginaWeb(dto.getPaginaWeb());
		try {
			entity = configuracionRepository.save(entity);
			entity.setId(dto.getId());
		}catch(Exception e) {
			throw new Exception();
		}
		return dto;
	}

	public ConfiguracionDTO update(long id, ConfiguracionDTO DTO) throws Exception {
		Optional<Configuracion> entityOptional = configuracionRepository.findById(id);
		try {
			Configuracion entity = entityOptional.get();
			entity.setId(DTO.getId());
			entity.setHorarioCierre(DTO.getHorarioCierre());
			entity.setHorarioApertura(DTO.getHorarioApertura());
			entity.setCantidadCocineros(DTO.getCantidadCocineros());
			entity.setNombreEmpresa(DTO.getNombreEmpresa());
			entity.setEmailEmpresa(DTO.getEmailEmpresa());
			entity.setCuit(DTO.getCuit());
			entity.setNumeroFiscal(DTO.getNumeroFiscal());
			entity.setSociedad(DTO.getSociedad());
			entity.setPaginaWeb(DTO.getPaginaWeb());
			configuracionRepository.save(entity);
		} catch (Exception e) {
			throw new Exception();
		}
		return DTO;
	}

	public boolean delete(long id) throws Exception {
		try {
			if (configuracionRepository.existsById(id)) {
				configuracionRepository.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
