package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.entity.Pedido;
import com.utn.demo.repository.PedidoRepository;

@Service
public class PedidoService {

	private PedidoRepository pedidoRepository;

	public PedidoService(PedidoRepository pedidoRepository){
		this.pedidoRepository = pedidoRepository;
	}

	@Transactional
	public List<PedidoDTO> findAll() throws Exception{
		
		List<Pedido> entities = pedidoRepository.findAll();
		List<PedidoDTO> dtos = new ArrayList<>();
		
		try {
			
			for(Pedido i : entities) {
				
				PedidoDTO unDto = new PedidoDTO();
				
				unDto.setNumeroPedido(i.getNumeroPedido());
				unDto.setHoraEstimada(i.getHoraEstimada());
				unDto.setSubtotal(i.getSubtotal());

				dtos.add(unDto);
			}
			 return dtos;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public PedidoDTO findById(long id) throws Exception{
		
		Optional<Pedido> entityOptional = pedidoRepository.findById(id);
		PedidoDTO unDto = new PedidoDTO();
		
		try {
			Pedido entidad = entityOptional.get();
			
			unDto.setNumeroPedido(entidad.getNumeroPedido());
			unDto.setHoraEstimada(entidad.getHoraEstimada());
			unDto.setSubtotal(entidad.getSubtotal());
			
			return unDto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public PedidoDTO save(PedidoDTO dto) throws Exception{
		
		Pedido entity = new Pedido();
		
		entity.setHoraEstimada(dto.getHoraEstimada());
		entity.setSubtotal(dto.getSubtotal());
		
		try {
			
			entity = pedidoRepository.save(entity);
			dto.setNumeroPedido(entity.getNumeroPedido());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public PedidoDTO update(long id, PedidoDTO dto) throws Exception{
		
		Optional<Pedido> entityOptional = pedidoRepository.findById(id);
		try {
			Pedido entidad = entityOptional.get();
			
			entidad.setNumeroPedido(dto.getNumeroPedido());
			entidad.setHoraEstimada(dto.getHoraEstimada());
			entidad.setSubtotal(dto.getSubtotal());
			
			
			
			pedidoRepository.save(entidad);
			dto.setNumeroPedido(entidad.getNumeroPedido());
			
			return dto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public boolean delete(long id) throws Exception{
		try {
			if(pedidoRepository.existsById(id)) {
				pedidoRepository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
