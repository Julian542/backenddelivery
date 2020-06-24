package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.TarjetaDTO;
import com.utn.demo.entity.Tarjeta;
import com.utn.demo.repository.TarjetaRepository;

@Service
public class TarjetaService {

	private TarjetaRepository tarjetaRepository;

	public TarjetaService(TarjetaRepository tarjetaRepository){
		this.tarjetaRepository = tarjetaRepository;
	}

	@Transactional
	public List<TarjetaDTO> findAll() throws Exception{
		
		List<Tarjeta> entities = tarjetaRepository.findAll();
		List<TarjetaDTO> dtos = new ArrayList<>();
		
		try {
			
			for(Tarjeta i : entities) {
				
				TarjetaDTO unDto = new TarjetaDTO();
				
				unDto.setId(i.getId());
				unDto.setMonto(i.getMonto());
				unDto.setDni(i.getDni());
				unDto.setNombreTitular(i.getNombreTitular());
				unDto.setNumeroTarjeta(i.getNumeroTarjeta());
				
				dtos.add(unDto);
			}
			 return dtos;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public TarjetaDTO findById(long id) throws Exception{
		
		Optional<Tarjeta> entityOptional = tarjetaRepository.findById(id);
		TarjetaDTO unDto = new TarjetaDTO();
		
		try {
			Tarjeta entidad = entityOptional.get();
			
			unDto.setId(entidad.getId());
			unDto.setMonto(entidad.getMonto());
			unDto.setDni(entidad.getDni());
			unDto.setNombreTitular(entidad.getNombreTitular());
			unDto.setNumeroTarjeta(entidad.getNumeroTarjeta());
			
			
			return unDto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public TarjetaDTO save(TarjetaDTO dto) throws Exception{
		
		Tarjeta entity = new Tarjeta();

		entity.setMonto(dto.getMonto());
		entity.setDni(dto.getDni());
		entity.setNombreTitular(dto.getNombreTitular());
		entity.setNumeroTarjeta(dto.getNumeroTarjeta());
		
		
		try {
			
			entity = tarjetaRepository.save(entity);
			dto.setId(entity.getId());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public TarjetaDTO update(long id, TarjetaDTO dto) throws Exception{
		
		Optional<Tarjeta> entityOptional = tarjetaRepository.findById(id);
		try {
			Tarjeta entidad = entityOptional.get();
			
			entidad.setId(dto.getId());
			entidad.setMonto(dto.getMonto());
			entidad.setDni(dto.getDni());
			entidad.setNombreTitular(dto.getNombreTitular());
			entidad.setNumeroTarjeta(dto.getNumeroTarjeta());
			
			
			tarjetaRepository.save(entidad);
			dto.setId(entidad.getId());
			
			return dto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public boolean delete(long id) throws Exception{
		try {
			if(tarjetaRepository.existsById(id)) {
				tarjetaRepository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
