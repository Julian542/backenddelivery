package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.ClienteDTO;
import com.utn.demo.dtos.DetallePedidoDTO;
import com.utn.demo.dtos.DomicilioDTO;
import com.utn.demo.dtos.FacturaDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.LocalidadDTO;
import com.utn.demo.dtos.PedidoDTO;
import com.utn.demo.entity.Cliente;
import com.utn.demo.entity.DetallePedido;
import com.utn.demo.entity.Domicilio;
import com.utn.demo.entity.Factura;
import com.utn.demo.entity.Pedido;
import com.utn.demo.repository.ClienteRepository;

@Service
public class ClienteService {
	
	protected final ClienteRepository repo;
	
	public ClienteService( ClienteRepository repo ) {
		this.repo = repo;
	}
	
	//buscarporemail
	@Transactional
	public ClienteDTO buscarPorEmail(String email) {
		Optional<Cliente> entityOptional = repo.buscarPorEmail(email);
		try {
			Cliente c = entityOptional.get();
			ClienteDTO dto = new ClienteDTO();
			dto.setId(c.getId());
			dto.setApellido(c.getApellido());
			dto.setDni(c.getDni());
			List<DomicilioDTO> domiciliosdto = new ArrayList();
			for(Domicilio domi : c.getDomicilios() ) {
				DomicilioDTO unDto = new DomicilioDTO();
				unDto.setId(domi.getId());
				unDto.setCalle(domi.getCalle());
				unDto.setDepartamento(domi.getDepartamento());
				unDto.setNumero(domi.getNumero());
				unDto.setPiso(domi.getPiso());
				LocalidadDTO ld = new LocalidadDTO();
				ld.setId(domi.getLocalidad().getId());
				ld.setNombre(domi.getLocalidad().getNombre());
				unDto.setLocalidad(ld);
				domiciliosdto.add(unDto);
			}
			dto.setDomicilios(domiciliosdto);
			dto.setEmail(c.getEmail());
			dto.setEsCliente(true);
			List<FacturaDTO> facturitas = new ArrayList();
			for(Factura f : c.getFacturas()) {
				FacturaDTO facturovich = new FacturaDTO();
				facturovich.setNumeroFactura(f.getNumeroFactura());
				facturovich.setFecha(f.getFecha());
				facturitas.add(facturovich);
			}
			dto.setFacturas(facturitas);
			dto.setFechaNacimiento(c.getFechaNacimiento());
			dto.setImagen(c.getImagen());
			dto.setNombre(c.getNombre());
			dto.setPassword(c.getPassword());
			List<PedidoDTO> pedidoslista = new ArrayList();
			for(Pedido p : c.getPedidos()) {
				PedidoDTO unPedido = new PedidoDTO();
				unPedido.setNumeroPedido(p.getNumeroPedido());
				unPedido.setTipoEnvio(p.getTipoEnvio());
				unPedido.setSubtotal(p.getSubtotal());
				pedidoslista.add(unPedido);
			}
			dto.setPedidos(pedidoslista);
			dto.setTelefono(c.getTelefono());
			return dto;
		}catch(Exception e) {
			System.out.println("El usuario que usted busca no existe.");
			throw new Error();
		}
		
	}
	//getAll
	@Transactional
	public List<ClienteDTO> findAll(){
		List<Cliente> clientes = repo.findAll();
		List<ClienteDTO> dtos = new ArrayList();
		
		for(Cliente c : clientes) {
			ClienteDTO dto = new ClienteDTO();
			dto.setId(c.getId());
			dto.setApellido(c.getApellido());
			dto.setDni(c.getDni());
			List<DomicilioDTO> domiciliosdto = new ArrayList();
			for(Domicilio domi : c.getDomicilios() ) {
				DomicilioDTO unDto = new DomicilioDTO();
				unDto.setId(domi.getId());
				unDto.setCalle(domi.getCalle());
				unDto.setDepartamento(domi.getDepartamento());
				unDto.setNumero(domi.getNumero());
				unDto.setPiso(domi.getPiso());
				LocalidadDTO ld = new LocalidadDTO();
				ld.setId(domi.getLocalidad().getId());
				ld.setNombre(domi.getLocalidad().getNombre());
				unDto.setLocalidad(ld);
				domiciliosdto.add(unDto);
			}
			dto.setDomicilios(domiciliosdto);
			dto.setEmail(c.getEmail());
			dto.setEsCliente(true);
			List<FacturaDTO> facturitas = new ArrayList();
			for(Factura f : c.getFacturas()) {
				FacturaDTO facturovich = new FacturaDTO();
				facturovich.setNumeroFactura(f.getNumeroFactura());
				facturovich.setFecha(f.getFecha());
				facturitas.add(facturovich);
			}
			dto.setFacturas(facturitas);
			dto.setFechaNacimiento(c.getFechaNacimiento());
			dto.setImagen(c.getImagen());
			dto.setNombre(c.getNombre());
			dto.setPassword(c.getPassword());
			List<PedidoDTO> pedidoslista = new ArrayList();
			for(Pedido p : c.getPedidos()) {
				PedidoDTO unPedido = new PedidoDTO();
				unPedido.setNumeroPedido(p.getNumeroPedido());
				unPedido.setTipoEnvio(p.getTipoEnvio());
				unPedido.setSubtotal(p.getSubtotal());
				pedidoslista.add(unPedido);
			}
			dto.setPedidos(pedidoslista);
			dto.setTelefono(c.getTelefono());
			dtos.add(dto);
		}
		return dtos;
	}
	
	//getOne
	@Transactional
	public ClienteDTO findById(long id) {
		Optional<Cliente> entityOptional = repo.findById(id);
		try {
			Cliente c = entityOptional.get();
			ClienteDTO dto = new ClienteDTO();
			dto.setId(c.getId());
			dto.setApellido(c.getApellido());
			dto.setDni(c.getDni());
			List<DomicilioDTO> domiciliosdto = new ArrayList();
			for(Domicilio domi : c.getDomicilios() ) {
				DomicilioDTO unDto = new DomicilioDTO();
				unDto.setId(domi.getId());
				unDto.setCalle(domi.getCalle());
				unDto.setDepartamento(domi.getDepartamento());
				unDto.setNumero(domi.getNumero());
				unDto.setPiso(domi.getPiso());
				LocalidadDTO ld = new LocalidadDTO();
				ld.setId(domi.getLocalidad().getId());
				ld.setNombre(domi.getLocalidad().getNombre());
				unDto.setLocalidad(ld);
				domiciliosdto.add(unDto);
			}
			dto.setDomicilios(domiciliosdto);
			dto.setEmail(c.getEmail());
			dto.setEsCliente(true);
			List<FacturaDTO> facturitas = new ArrayList();
			for(Factura f : c.getFacturas()) {
				FacturaDTO facturovich = new FacturaDTO();
				facturovich.setNumeroFactura(f.getNumeroFactura());
				facturovich.setFecha(f.getFecha());
				facturitas.add(facturovich);
			}
			dto.setFacturas(facturitas);
			dto.setFechaNacimiento(c.getFechaNacimiento());
			dto.setImagen(c.getImagen());
			dto.setNombre(c.getNombre());
			dto.setPassword(c.getPassword());
			List<PedidoDTO> pedidoslista = new ArrayList();
			for(Pedido p : c.getPedidos()) {
				PedidoDTO unPedido = new PedidoDTO();
				unPedido.setNumeroPedido(p.getNumeroPedido());
				unPedido.setTipoEnvio(p.getTipoEnvio());
				unPedido.setSubtotal(p.getSubtotal());
				pedidoslista.add(unPedido);
			}
			dto.setPedidos(pedidoslista);
			dto.setTelefono(c.getTelefono());
			return dto;
		}catch(Exception e) {
			System.out.println("El usuario que usted busca no existe.");
			throw new Error();
		}
	}
	
	//save
	@Transactional
	public ClienteDTO save(ClienteDTO dto) {
		Cliente nuevoCliente = new Cliente();
		
		nuevoCliente.setApellido(dto.getApellido());
		nuevoCliente.setDni(dto.getDni());
		nuevoCliente.setEmail(dto.getEmail());
		nuevoCliente.setEsCliente(true);
		nuevoCliente.setFechaNacimiento(dto.getFechaNacimiento());
		nuevoCliente.setImagen(dto.getImagen());
		nuevoCliente.setNombre(dto.getNombre());
		nuevoCliente.setPassword(dto.getPassword());
		nuevoCliente.setTelefono(dto.getTelefono());
		
		nuevoCliente = repo.save(nuevoCliente);
		dto.setId(nuevoCliente.getId());
		return dto;
	}
	//update
	@Transactional
	public ClienteDTO update(long id, ClienteDTO dto) {
		Optional<Cliente> opcional = repo.findById(id);
		Cliente nuevoCliente = opcional.get();
		nuevoCliente.setId(dto.getId());
		nuevoCliente.setApellido(dto.getApellido());
		nuevoCliente.setDni(dto.getDni());
		nuevoCliente.setEmail(dto.getEmail());
		nuevoCliente.setEsCliente(true);
		nuevoCliente.setFechaNacimiento(dto.getFechaNacimiento());
		nuevoCliente.setImagen(dto.getImagen());
		nuevoCliente.setNombre(dto.getNombre());
		nuevoCliente.setPassword(dto.getPassword());
		nuevoCliente.setTelefono(dto.getTelefono());
		
		nuevoCliente = repo.save(nuevoCliente);
		dto.setId(nuevoCliente.getId());
		return dto;
	}
	//delete
	@Transactional
	public boolean delete(long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
