package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.ClienteDTO;
import com.utn.demo.dtos.DomicilioDTO;
import com.utn.demo.dtos.EmpleadoDTO;
import com.utn.demo.dtos.LocalidadDTO;
import com.utn.demo.entity.Domicilio;
import com.utn.demo.entity.Localidad;
import com.utn.demo.entity.Usuario;
import com.utn.demo.repository.DomicilioRepository;

@Service
public class DomicilioService {
	
	protected final DomicilioRepository repo;
	
	public DomicilioService(DomicilioRepository repo) {
		this.repo = repo;
	}
	
	//traer domicilios de los usuarios
	public List<DomicilioDTO> buscarporUsuario(long id){
		List<Domicilio> entidades = repo.buscarPorUsuario(id);
		List<DomicilioDTO> dtos = new ArrayList<>();
		for(Domicilio d : entidades) {
			DomicilioDTO unDto = new DomicilioDTO();
			unDto.setId(d.getId());
			unDto.setDepartamento(d.getDepartamento());
			unDto.setNumero(d.getNumero());
			unDto.setPiso(d.getPiso());
			unDto.setCalle(d.getCalle());
			LocalidadDTO localidadto = new LocalidadDTO();
			localidadto.setId(d.getLocalidad().getId());
			localidadto.setNombre(d.getLocalidad().getNombre());
			unDto.setLocalidad(localidadto);
			if( d.getUsuario().isEsCliente()) {
				ClienteDTO clientedto = new ClienteDTO();
				clientedto.setId(d.getUsuario().getId());
				clientedto.setNombre(d.getUsuario().getNombre());
				clientedto.setApellido(d.getUsuario().getApellido());
				unDto.setCliente(clientedto);
			}else {
				EmpleadoDTO empleado = new EmpleadoDTO();
				empleado.setId(d.getUsuario().getId());
				empleado.setNombre(d.getUsuario().getNombre());
				empleado.setApellido(d.getUsuario().getApellido());
				unDto.setEmpleado(empleado);
			}
			dtos.add(unDto);
		}
		return dtos;
	}
	
	
	//getAll
	@Transactional
	public List<DomicilioDTO> findAll(){
		List<Domicilio> entidades = repo.findAll();
		List<DomicilioDTO> dtos = new ArrayList<>();
		for(Domicilio d : entidades) {
			DomicilioDTO unDto = new DomicilioDTO();
			unDto.setId(d.getId());
			unDto.setDepartamento(d.getDepartamento());
			unDto.setNumero(d.getNumero());
			unDto.setPiso(d.getPiso());
			unDto.setCalle(d.getCalle());
			LocalidadDTO localidadto = new LocalidadDTO();
			localidadto.setId(d.getLocalidad().getId());
			localidadto.setNombre(d.getLocalidad().getNombre());
			unDto.setLocalidad(localidadto);
			if( d.getUsuario().isEsCliente()) {
				ClienteDTO clientedto = new ClienteDTO();
				clientedto.setId(d.getUsuario().getId());
				clientedto.setNombre(d.getUsuario().getNombre());
				clientedto.setApellido(d.getUsuario().getApellido());
				unDto.setCliente(clientedto);
			}else {
				EmpleadoDTO empleado = new EmpleadoDTO();
				empleado.setId(d.getUsuario().getId());
				empleado.setNombre(d.getUsuario().getNombre());
				empleado.setApellido(d.getUsuario().getApellido());
				unDto.setEmpleado(empleado);
			}
			dtos.add(unDto);
		}
		return dtos;
	}
	//getOne
	@Transactional
	public DomicilioDTO findById(long id) {
		Optional<Domicilio> entityOptional = repo.findById(id);
		Domicilio d = entityOptional.get();
		DomicilioDTO unDto = new DomicilioDTO();
		
		unDto.setId(d.getId());
		unDto.setDepartamento(d.getDepartamento());
		unDto.setNumero(d.getNumero());
		unDto.setPiso(d.getPiso());
		unDto.setCalle(d.getCalle());
		LocalidadDTO localidadto = new LocalidadDTO();
		localidadto.setId(d.getLocalidad().getId());
		localidadto.setNombre(d.getLocalidad().getNombre());
		unDto.setLocalidad(localidadto);
		if( d.getUsuario().isEsCliente()) {
			ClienteDTO clientedto = new ClienteDTO();
			clientedto.setId(d.getUsuario().getId());
			clientedto.setNombre(d.getUsuario().getNombre());
			clientedto.setApellido(d.getUsuario().getApellido());
			unDto.setCliente(clientedto);
		}else {
			EmpleadoDTO empleado = new EmpleadoDTO();
			empleado.setId(d.getUsuario().getId());
			empleado.setNombre(d.getUsuario().getNombre());
			empleado.setApellido(d.getUsuario().getApellido());
			unDto.setEmpleado(empleado);
		}
		return unDto;
	}
	//save
	@Transactional
	public DomicilioDTO save(DomicilioDTO dto) { //PARA DAR DE ALTA UN DOMICILIO ENVIAR EL CAMPO ESCLIENTE BOOLEAN EN EL JSON
		Domicilio dom = new Domicilio();
		
		dom.setCalle(dto.getCalle());
		dom.setDepartamento(dto.getDepartamento());
		dom.setNumero(dto.getNumero());
		dom.setPiso(dto.getPiso());
		Usuario usuario = new Usuario();
		if(dto.getCliente().isEsCliente()) {
			usuario.setId(dto.getCliente().getId());
		}
		if(dto.getEmpleado().isEsCliente() == false){
			usuario.setId(dto.getEmpleado().getId());
		}
		dom.setUsuario(usuario);
		Localidad loc = new Localidad();
		loc.setId(dto.getLocalidad().getId());
		loc.setNombre(dto.getLocalidad().getNombre());
		dom.setLocalidad(loc);
		
		dom = repo.save(dom);
		dto.setId(dom.getId());
		return dto;
	}
	//update
	@Transactional
	public DomicilioDTO update(long id, DomicilioDTO dto) {
		Optional<Domicilio> op = repo.findById(id);
		Domicilio dom = op.get();
		
		dom.setCalle(dto.getCalle());
		dom.setDepartamento(dto.getDepartamento());
		dom.setNumero(dto.getNumero());
		dom.setPiso(dto.getPiso());
		Usuario usuario = new Usuario();
		if(dto.getCliente().isEsCliente()) {
			usuario.setId(dto.getCliente().getId());
		}
		if(dto.getEmpleado().isEsCliente() == false){
			usuario.setId(dto.getEmpleado().getId());
		}
		dom.setUsuario(usuario);
		Localidad loc = new Localidad();
		loc.setId(dto.getLocalidad().getId());
		loc.setNombre(dto.getLocalidad().getNombre());
		dom.setLocalidad(loc);
		
		dom = repo.save(dom);
		dto.setId(dom.getId());
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
