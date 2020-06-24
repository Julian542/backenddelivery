package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.utn.demo.dtos.ClienteDTO;
import com.utn.demo.dtos.ConfiguracionDTO;
import com.utn.demo.dtos.CocinaDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.entity.Cliente;
import com.utn.demo.entity.Configuracion;
import com.utn.demo.entity.Cocina;
import com.utn.demo.entity.Pedido;
import com.utn.demo.repository.CocinaRepository;

public class CocinaService {
	private CocinaRepository cocinaRepository;

	public CocinaService(CocinaRepository cocinaRepository){
		this.cocinaRepository = cocinaRepository;
	}

	@Transactional
	public List<CocinaDTO> findAll() throws Exception{
		
		List<Cocina> entities = cocinaRepository.findAll();
		List<CocinaDTO> dtos = new ArrayList<>();
		
		try {
			
			for(Cocina i : entities) {
				
				CocinaDTO unDto = new CocinaDTO();
				
				unDto.setId(i.getId());
				unDto.setTiempo(i.getTiempo());
				
				dtos.add(unDto);
			}
			 return dtos;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public CocinaDTO findById(long id) throws Exception{
		
		Optional<Cocina> entityOptional = cocinaRepository.findById(id);
		CocinaDTO unDto = new CocinaDTO();
		
		try {
			Cocina entidad = entityOptional.get();
			
			unDto.setId(entidad.getId());
			unDto.setTiempo(entidad.getTiempo());
			
			
			return unDto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public CocinaDTO save(CocinaDTO dto) throws Exception{
		
		Cocina entity = new Cocina();

		entity.setTiempo(dto.getTiempo());
		
		
		try {
			
			entity = cocinaRepository.save(entity);
			dto.setId(entity.getId());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public CocinaDTO update(long id, CocinaDTO dto) throws Exception{
		
		Optional<Cocina> entityOptional = cocinaRepository.findById(id);
		try {
			Cocina entidad = entityOptional.get();
			
			entidad.setId(dto.getId());
			entidad.setTiempo(dto.getTiempo());
			
			cocinaRepository.save(entidad);
			dto.setId(entidad.getId());
			
			return dto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public boolean delete(long id) throws Exception{
		try {
			if(cocinaRepository.existsById(id)) {
				cocinaRepository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
