package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.ClienteDTO;
import com.utn.demo.dtos.EstadoDTO;
import com.utn.demo.dtos.FacturaDTO;
import com.utn.demo.dtos.FormaPagoDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.entity.Cliente;
import com.utn.demo.entity.Cocina;
import com.utn.demo.entity.Estado;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.FormaPago;
import com.utn.demo.entity.Pedido;
import com.utn.demo.repository.CocinaRepository;
import com.utn.demo.repository.FacturaRepository;
import com.utn.demo.repository.PedidoRepository;

@Service
public class PedidoService {

	private PedidoRepository pedidoRepository;
	protected final CocinaRepository repositoryCocina;

	public PedidoService(PedidoRepository pedidoRepository,CocinaRepository repositoryCocina){
		this.pedidoRepository = pedidoRepository;
		this.repositoryCocina = repositoryCocina;
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
				unDto.setCocinaRelacionada(i.getCocina().getId());
				
				Estado estado = i.getEstado();
				EstadoDTO estadoDto = new EstadoDTO();
				estadoDto.setId(estado.getId());
				estadoDto.setEstadoPedido(estado.getEstadoPedido());
				unDto.setEstado(estadoDto);
				
				Cliente cliente = i.getPedidoCliente();
				ClienteDTO clienteDto = new ClienteDTO();
				clienteDto.setId(cliente.getId());
				clienteDto.setApellido(cliente.getApellido());
				clienteDto.setDni(cliente.getDni());
				unDto.setPedidoCliente(clienteDto);
				
				Factura factura = i.getFactura();
				FacturaDTO facturaDto = new FacturaDTO();
				facturaDto.setNumeroFactura(factura.getNumeroFactura());
				unDto.setFactura(facturaDto);
				
				FormaPago formaPago = i.getFormaPago();
				FormaPagoDTO formaPagoDto = new FormaPagoDTO();
				formaPagoDto.setId(formaPago.getId());
				formaPagoDto.setMonto(formaPago.getMonto());
				unDto.setFormaPago(formaPagoDto);
				
				

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
			unDto.setCocinaRelacionada(entidad.getCocina().getId());
			
			Estado estado = entidad.getEstado();
			EstadoDTO estadoDto = new EstadoDTO();
			estadoDto.setId(estado.getId());
			estadoDto.setEstadoPedido(estado.getEstadoPedido());
			unDto.setEstado(estadoDto);
			
			Cliente cliente = entidad.getPedidoCliente();
			ClienteDTO clienteDto = new ClienteDTO();
			clienteDto.setId(cliente.getId());
			clienteDto.setApellido(cliente.getApellido());
			clienteDto.setDni(cliente.getDni());
			unDto.setPedidoCliente(clienteDto);
			
			Factura factura = entidad.getFactura();
			FacturaDTO facturaDto = new FacturaDTO();
			facturaDto.setNumeroFactura(factura.getNumeroFactura());
			unDto.setFactura(facturaDto);
			
			FormaPago formaPago = entidad.getFormaPago();
			FormaPagoDTO formaPagoDto = new FormaPagoDTO();
			formaPagoDto.setId(formaPago.getId());
			formaPagoDto.setMonto(formaPago.getMonto());
			unDto.setFormaPago(formaPagoDto);
			
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
		
		// Creamos un cliente
		Cliente clientenuevo = new Cliente();
		clientenuevo.setId(dto.getPedidoCliente().getId());
		entity.setPedidoCliente(clientenuevo);

		Optional<Cocina> cocina = repositoryCocina.findById(dto.getCocinaRelacionada());
		Cocina relacion = cocina.get();
		entity.setCocina(relacion);
		
		// Creamos un estado
		Estado estadonuevo = new Estado();
		estadonuevo.setId(dto.getEstado().getId());
		entity.setEstado(estadonuevo);

		// Creamos una factura
		Factura facturanueva = new Factura();
		facturanueva.setNumeroFactura(dto.getFactura().getNumeroFactura());
		entity.setFactura(facturanueva);

		// Creamos una formaPago
		FormaPago formapagonueva = new FormaPago();
		formapagonueva.setId(dto.getFormaPago().getId());
		entity.setFormaPago(formapagonueva);

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
			
			Optional<Cocina> cocina = repositoryCocina.findById(dto.getCocinaRelacionada());
			Cocina relacion = cocina.get();
			entidad.setCocina(relacion);
			
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
