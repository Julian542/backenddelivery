package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.ContadoDTO;
import com.utn.demo.entity.Contado;
import com.utn.demo.repository.ContadoRepository;

@Service
public class ContadoService {

	private ContadoRepository contadoRepository;

	public ContadoService(ContadoRepository contadoRepository){
		this.contadoRepository = contadoRepository;
	}

	@Transactional
	public List<ContadoDTO> findAll() throws Exception{
		
		List<Contado> entities = contadoRepository.findAll();
		List<ContadoDTO> dtos = new ArrayList<>();
		
		try {
			
			for(Contado i : entities) {
				
				ContadoDTO unDto = new ContadoDTO();
				
				unDto.setId(i.getId());
				unDto.setDescuento(i.getDescuento());
				unDto.setMonto(i.getMonto());
				
				dtos.add(unDto);
			}
			 return dtos;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public ContadoDTO findById(long id) throws Exception{
		
		Optional<Contado> entityOptional = contadoRepository.findById(id);
		ContadoDTO unDto = new ContadoDTO();
		
		try {
			Contado entidad = entityOptional.get();
			
			unDto.setId(entidad.getId());
			unDto.setDescuento(entidad.getDescuento());
			unDto.setMonto(entidad.getMonto());
			
			
			return unDto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public ContadoDTO save(ContadoDTO dto) throws Exception{
		
		Contado entity = new Contado();

		entity.setDescuento(dto.getDescuento());
		entity.setMonto(dto.getMonto());
		
		
		try {
			
			entity = contadoRepository.save(entity);
			dto.setId(entity.getId());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public ContadoDTO update(long id, ContadoDTO dto) throws Exception{
		
		Optional<Contado> entityOptional = contadoRepository.findById(id);
		try {
			Contado entidad = entityOptional.get();
			
			entidad.setId(dto.getId());
			entidad.setDescuento(dto.getDescuento());
			entidad.setMonto(dto.getMonto());
			
			contadoRepository.save(entidad);
			dto.setId(entidad.getId());
			
			return dto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public boolean delete(long id) throws Exception{
		try {
			if(contadoRepository.existsById(id)) {
				contadoRepository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
