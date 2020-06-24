package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.EstadoDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.entity.Estado;
import com.utn.demo.entity.Pedido;
import com.utn.demo.repository.EstadoRepository;

@Service
public class EstadoService {

	private EstadoRepository estadoRepository;

	public EstadoService(EstadoRepository estadoRepository){
		this.estadoRepository = estadoRepository;
	}

	@Transactional
	public List<EstadoDTO> findAll() throws Exception{
		
		List<Estado> entities = estadoRepository.findAll();
		List<EstadoDTO> dtos = new ArrayList<>();
		
		try {
			
			for(Estado i : entities) {
				
				EstadoDTO unDto = new EstadoDTO();
				
				unDto.setId(i.getId());
				unDto.setEstadoPedido(i.getEstadoPedido());
				
				Pedido pedido = i.getPedido();
				PedidoDTO pedidoDto= new PedidoDTO();
				pedidoDto.setNumeroPedido(pedido.getNumeroPedido());
				unDto.setPedido(pedidoDto);
				
				dtos.add(unDto);
			}
			 return dtos;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public EstadoDTO findById(long id) throws Exception{
		
		Optional<Estado> entityOptional = estadoRepository.findById(id);
		EstadoDTO unDto = new EstadoDTO();
		
		try {
			Estado entidad = entityOptional.get();
			
			unDto.setId(entidad.getId());
			unDto.setEstadoPedido(entidad.getEstadoPedido());			
			
			Pedido pedido = entidad.getPedido();
			PedidoDTO pedidoDto= new PedidoDTO();
			pedidoDto.setNumeroPedido(pedido.getNumeroPedido());
			unDto.setPedido(pedidoDto);
			
			return unDto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public EstadoDTO save(EstadoDTO dto) throws Exception{
		
		Estado entity = new Estado();


		entity.setEstadoPedido(dto.getEstadoPedido());
		
		// Pedido
		Pedido p = new Pedido();
		p.setNumeroPedido(dto.getPedido().getNumeroPedido());
		entity.setPedido(p);
		
		try {
			
			entity = estadoRepository.save(entity);
			dto.setId(entity.getId());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public EstadoDTO update(long id, EstadoDTO dto) throws Exception{
		
		Optional<Estado> entityOptional = estadoRepository.findById(id);
		try {
			Estado entidad = entityOptional.get();
			
			entidad.setId(dto.getId());
			entidad.setEstadoPedido(dto.getEstadoPedido());
			
			estadoRepository.save(entidad);
			dto.setId(entidad.getId());
			
			return dto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public boolean delete(long id) throws Exception{
		try {
			if(estadoRepository.existsById(id)) {
				estadoRepository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
