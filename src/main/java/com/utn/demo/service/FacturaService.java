package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.ClienteDTO;
import com.utn.demo.dtos.ConfiguracionDTO;
import com.utn.demo.dtos.DetalleFacturaDTO;
import com.utn.demo.dtos.FacturaDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.entity.Cliente;
import com.utn.demo.entity.Configuracion;
import com.utn.demo.entity.DetalleFactura;
import com.utn.demo.entity.Domicilio;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.FormaPago;
import com.utn.demo.entity.Pedido;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.Factura;
import com.utn.demo.repository.FacturaRepository;

@Service
public class FacturaService {

	private FacturaRepository facturaRepository;

	public FacturaService(FacturaRepository facturaRepository){
		this.facturaRepository = facturaRepository;
	}

	@Transactional
	public List<FacturaDTO> findAll() throws Exception{
		
		List<Factura> entities = facturaRepository.findAll();
		List<FacturaDTO> dtos = new ArrayList<>();
		
		try {
			
			for(Factura i : entities) {
				
				FacturaDTO unDto = new FacturaDTO();
				
				unDto.setNumeroFactura(i.getNumeroFactura());
				unDto.setFecha(i.getFecha());
				unDto.setTipo(i.getTipo());
				unDto.setMontoDescuento(i.getMontoDescuento());
				unDto.setTotal(i.getTotal());
				
				Cliente cliente = i.getFacturaCliente();
				ClienteDTO clienteDto = new ClienteDTO();
				clienteDto.setId(cliente.getId());
				clienteDto.setApellido(cliente.getApellido());
				clienteDto.setDni(cliente.getDni());
				unDto.setFacturaCliente(clienteDto);
				
				Pedido pedido = i.getPedido();
				PedidoDTO pedidoDto= new PedidoDTO();
				pedidoDto.setNumeroPedido(pedido.getNumeroPedido());
				unDto.setPedido(pedidoDto);
				
				Configuracion config = i.getEmpresa();
				ConfiguracionDTO configDto = new ConfiguracionDTO();
				configDto.setId(config.getId());
				configDto.setCantidadCocineros(config.getCantidadCocineros());
				configDto.setCuit(config.getCuit());
				configDto.setEmailEmpresa(config.getEmailEmpresa());
				configDto.setHorarioApertura(config.getHorarioApertura());
				configDto.setHorarioCierre(config.getHorarioCierre());
				configDto.setNombreEmpresa(config.getNombreEmpresa());
				configDto.setNumeroFiscal(config.getNumeroFiscal());
				configDto.setPaginaWeb(config.getPaginaWeb());
				configDto.setSociedad(config.getSociedad());
				configDto.setTelefono(config.getTelefono());
				unDto.setEmpresa(configDto);
				
				dtos.add(unDto);
			}
			 return dtos;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public FacturaDTO findById(long id) throws Exception{
		
		Optional<Factura> entityOptional = facturaRepository.findById(id);
		FacturaDTO unDto = new FacturaDTO();
		
		try {
			Factura entidad = entityOptional.get();
			
			unDto.setNumeroFactura(entidad.getNumeroFactura());
			unDto.setFecha(entidad.getFecha());
			unDto.setTipo(entidad.getTipo());
			unDto.setMontoDescuento(entidad.getMontoDescuento());
			unDto.setTotal(entidad.getTotal());
			
			Cliente cliente = entidad.getFacturaCliente();
			ClienteDTO clienteDto = new ClienteDTO();
			clienteDto.setId(cliente.getId());
			clienteDto.setApellido(cliente.getApellido());
			clienteDto.setDni(cliente.getDni());
			unDto.setFacturaCliente(clienteDto);
			
			Pedido pedido = entidad.getPedido();
			PedidoDTO pedidoDto= new PedidoDTO();
			pedidoDto.setNumeroPedido(pedido.getNumeroPedido());
			unDto.setPedido(pedidoDto);
			
			Configuracion config = entidad.getEmpresa();
			ConfiguracionDTO configDto = new ConfiguracionDTO();
			configDto.setId(config.getId());
			configDto.setCantidadCocineros(config.getCantidadCocineros());
			configDto.setCuit(config.getCuit());
			configDto.setEmailEmpresa(config.getEmailEmpresa());
			configDto.setHorarioApertura(config.getHorarioApertura());
			configDto.setHorarioCierre(config.getHorarioCierre());
			configDto.setNombreEmpresa(config.getNombreEmpresa());
			configDto.setNumeroFiscal(config.getNumeroFiscal());
			configDto.setPaginaWeb(config.getPaginaWeb());
			configDto.setSociedad(config.getSociedad());
			configDto.setTelefono(config.getTelefono());
			unDto.setEmpresa(configDto);
			
			return unDto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public FacturaDTO save(FacturaDTO dto) throws Exception{
		
		Factura entity = new Factura();
		entity.setFecha(dto.getFecha());
		entity.setTipo(dto.getTipo());
		entity.setMontoDescuento(dto.getMontoDescuento());
		entity.setTotal(dto.getTotal());
		
		//Creamos un cliente
		Cliente clientenuevo = new Cliente();
		clientenuevo.setId(dto.getFacturaCliente().getId());
		entity.setFacturaCliente(clientenuevo);
		
		//Configuracion
		Configuracion config = new Configuracion();
		config.setId(dto.getEmpresa().getId());
		entity.setEmpresa(config);
		
		//Pedido
		Pedido p = new Pedido();
		p.setNumeroPedido(dto.getPedido().getNumeroPedido());
		entity.setPedido(p);
		
		
		try {
			
			entity = facturaRepository.save(entity);
			dto.setNumeroFactura(entity.getNumeroFactura());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public FacturaDTO update(long id, FacturaDTO dto) throws Exception{
		
		Optional<Factura> entityOptional = facturaRepository.findById(id);
		try {
			Factura entidad = entityOptional.get();
			entidad.setNumeroFactura(dto.getNumeroFactura());
			entidad.setFecha(dto.getFecha());
			entidad.setTipo(dto.getTipo());
			entidad.setMontoDescuento(dto.getMontoDescuento());
			entidad.setTotal(dto.getTotal());
			
			facturaRepository.save(entidad);
			dto.setNumeroFactura(entidad.getNumeroFactura());
			
			return dto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public boolean delete(long id) throws Exception{
		try {
			if(facturaRepository.existsById(id)) {
				facturaRepository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
}