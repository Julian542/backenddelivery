package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.ClienteDTO;
import com.utn.demo.dtos.DetallePedidoDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.entity.Cliente;
import com.utn.demo.entity.DetallePedido;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.Pedido;
import com.utn.demo.entity.Plato;
import com.utn.demo.repository.DetallePedidoRepository;
import com.utn.demo.repository.PedidoRepository;

@Service
public class DetallePedidoService {

	protected final DetallePedidoRepository repositoryDetallePedido;
	protected final PedidoRepository repositoryPedido;
	
	public DetallePedidoService(DetallePedidoRepository repository,PedidoRepository repositoryPedido) {
		this.repositoryDetallePedido = repository;
		this.repositoryPedido = repositoryPedido;
	}
	
	@Transactional
	public List<DetallePedidoDTO> findAll() throws Exception{
		List<DetallePedido> entities = repositoryDetallePedido.findAll();
		List<DetallePedidoDTO> dtos = new ArrayList<>();
		try {
			for( DetallePedido d : entities) {
				DetallePedidoDTO unDto = new DetallePedidoDTO();
				unDto.setId(d.getId());
				unDto.setCantidad(d.getCantidad());
				unDto.setSubtotal(d.getSubtotal());
				unDto.setPedidoRelacionado(d.getPedido().getNumeroPedido());
				
				Plato plato = d.getPlato();
				PlatoDTO platoDto = new PlatoDTO();
				platoDto.setId(plato.getId());
				unDto.setPlato(platoDto);
				
				Insumo insumo = d.getInsumo();
				InsumoDTO insumoDto = new InsumoDTO();
				insumoDto.setId(insumo.getId());
				unDto.setInsumo(insumoDto);
				
				dtos.add(unDto);
			}
			return dtos;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	/*@Transactional
	public List<DetallePedidoDTO> buscarPorIdP(long id ) throws Exception{
		List<DetallePedido> entities = repositoryDetallePedido.buscarporIdP(id);
		List<DetallePedidoDTO> dtos = new ArrayList<>();
		try {
			for( DetallePedido d : entities) {
				DetallePedidoDTO unDto = new DetallePedidoDTO();
				unDto.setId(d.getId());
				unDto.setCantidad(d.getCantidad());
				unDto.setSubtotal(d.getSubtotal());
				unDto.setPedidoRelacionado(d.getPedido().getNumeroPedido());
				dtos.add(unDto);
			}
			return dtos;
		}catch(Exception e) {
			throw new Exception();
		}
	}*/
	
	@Transactional
	public DetallePedidoDTO findById(long id) throws Exception{
		Optional<DetallePedido> entityOptional = repositoryDetallePedido.findById(id);
		DetallePedidoDTO unDto = new DetallePedidoDTO();
		try {
			DetallePedido d = entityOptional.get();
			unDto.setId(d.getId());
			unDto.setCantidad(d.getCantidad());
			unDto.setSubtotal(d.getSubtotal());
			unDto.setPedidoRelacionado(d.getPedido().getNumeroPedido());
			
			Plato plato = d.getPlato();
			PlatoDTO platoDto = new PlatoDTO();
			platoDto.setId(plato.getId());
			unDto.setPlato(platoDto);
			
			Insumo insumo = d.getInsumo();
			InsumoDTO insumoDto = new InsumoDTO();
			insumoDto.setId(insumo.getId());
			unDto.setInsumo(insumoDto);
			
			return unDto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public DetallePedidoDTO save(DetallePedidoDTO dto) throws Exception{
		DetallePedido entity = new DetallePedido();
		entity.setCantidad(dto.getCantidad());
		entity.setSubtotal(dto.getSubtotal());
		
		Optional<Pedido> Pedido = repositoryPedido.findById(dto.getPedidoRelacionado());
		Pedido relacion = Pedido.get();
		entity.setPedido(relacion);

		// Creamos un plato
		Plato platonuevo = new Plato();
		platonuevo.setId(dto.getPlato().getId());
		entity.setPlato(platonuevo);
		
		// Creamos un insumo
		Insumo insumonuevo = new Insumo();
		insumonuevo.setId(dto.getInsumo().getId());
		entity.setInsumo(insumonuevo);
		
		try {
			entity = repositoryDetallePedido.save(entity);
			dto.setId(entity.getId());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public DetallePedidoDTO update(long id, DetallePedidoDTO dto) throws Exception{
		Optional<DetallePedido> entityOptional = repositoryDetallePedido.findById(id);
		try {
			DetallePedido entidad = entityOptional.get();
			entidad.setId(dto.getId());
			entidad.setCantidad(dto.getCantidad());
			entidad.setSubtotal(dto.getSubtotal());
			
			Optional<Pedido> Pedido = repositoryPedido.findById(dto.getPedidoRelacionado());
			Pedido relacion = Pedido.get();
			entidad.setPedido(relacion);
			
			repositoryDetallePedido.save(entidad);
			dto.setId(entidad.getId());
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public boolean delete(long id) throws Exception{
		try {
			if(repositoryDetallePedido.existsById(id)) {
				repositoryDetallePedido.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
