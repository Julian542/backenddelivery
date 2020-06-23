package com.utn.demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

import javax.transaction.Transactional;

import com.utn.demo.dtos.DomicilioDTO;
import com.utn.demo.dtos.EmpleadoDTO;
import com.utn.demo.dtos.LocalidadDTO;
import com.utn.demo.entity.Domicilio;
import com.utn.demo.entity.Empleado;
import com.utn.demo.repository.EmpleadoRepository;

@Service
public class EmpleadoService {
	
	protected final EmpleadoRepository repositorio;
	
	public EmpleadoService(EmpleadoRepository repositorio) {
		this.repositorio = repositorio;
	}
	
	@Transactional
	public List<EmpleadoDTO> getAll(){
		
		List<EmpleadoDTO> dtos = new ArrayList();
		
		for(Empleado e : repositorio.findAll() ) {
			EmpleadoDTO unDto = new EmpleadoDTO();
			unDto.setId(e.getId());
			unDto.setApellido(e.getApellido());
			unDto.setDni(e.getDni());
			List<DomicilioDTO> domiciliosdto = new ArrayList();
			for(Domicilio d : e.getDomicilios()) {
				DomicilioDTO dtodom = new DomicilioDTO();
				dtodom.setCalle(d.getCalle());
				dtodom.setDepartamento(d.getDepartamento());
				dtodom.setId(d.getId());
				//
				LocalidadDTO localidaddto = new LocalidadDTO();
				localidaddto.setId(d.getLocalidad().getId());
				localidaddto.setNombre(d.getLocalidad().getNombre());
				//
				dtodom.setLocalidad(localidaddto);
				dtodom.setNumero(d.getNumero());
				dtodom.setPiso(d.getPiso());
				domiciliosdto.add(dtodom);
			}
			unDto.setDomicilios(domiciliosdto);
			unDto.setEmail(e.getEmail());
			unDto.setEsCliente(e.isEsCliente());
			unDto.setFechaNacimiento(e.getFechaNacimiento());
			unDto.setImagen(e.getImagen());
			unDto.setNombre(e.getNombre());
			unDto.setPassword(e.getPassword());
			unDto.setRol(e.getRol());
			unDto.setTelefono(e.getTelefono());
			dtos.add(unDto);
		}
		return dtos;
		
	}
	
	//getOne
	@Transactional
	public EmpleadoDTO getOne(long id) {
		Optional<Empleado> entiti = repositorio.findById(id);
		//cuerpo del metodo
		Empleado e = entiti.get();
		EmpleadoDTO unDto = new EmpleadoDTO();
		unDto.setId(e.getId());
		unDto.setApellido(e.getApellido());
		unDto.setDni(e.getDni());
		List<DomicilioDTO> domiciliosdto = new ArrayList();
		for(Domicilio d : e.getDomicilios()) {
			DomicilioDTO dtodom = new DomicilioDTO();
			dtodom.setCalle(d.getCalle());
			dtodom.setDepartamento(d.getDepartamento());
			dtodom.setId(d.getId());
			//
			LocalidadDTO localidaddto = new LocalidadDTO();
			localidaddto.setId(d.getLocalidad().getId());
			localidaddto.setNombre(d.getLocalidad().getNombre());
			//
			dtodom.setLocalidad(localidaddto);
			dtodom.setNumero(d.getNumero());
			dtodom.setPiso(d.getPiso());
			domiciliosdto.add(dtodom);
		}
		unDto.setDomicilios(domiciliosdto);
		unDto.setEmail(e.getEmail());
		unDto.setEsCliente(e.isEsCliente());
		unDto.setFechaNacimiento(e.getFechaNacimiento());
		unDto.setImagen(e.getImagen());
		unDto.setNombre(e.getNombre());
		unDto.setPassword(e.getPassword());
		unDto.setRol(e.getRol());
		unDto.setTelefono(e.getTelefono());
		//retorna
		return unDto;
	}
	//save
	@Transactional
	public EmpleadoDTO save( EmpleadoDTO dto ) {
		
		Empleado nuevoEmpleado = new Empleado();
		nuevoEmpleado.setApellido(dto.getApellido());
		nuevoEmpleado.setDni(dto.getDni());
		nuevoEmpleado.setEmail(dto.getEmail());
		nuevoEmpleado.setEsCliente(false);
		nuevoEmpleado.setFechaNacimiento(dto.getFechaNacimiento());
		nuevoEmpleado.setImagen(dto.getImagen());
		nuevoEmpleado.setNombre(dto.getNombre());
		nuevoEmpleado.setPassword(dto.getPassword());
		nuevoEmpleado.setRol(dto.getRol());
		nuevoEmpleado.setTelefono(dto.getTelefono());
		
		nuevoEmpleado = repositorio.save(nuevoEmpleado);
		dto.setId(nuevoEmpleado.getId());
		
		return dto;
	}
	//update
	
	@Transactional
	public EmpleadoDTO update(long id, EmpleadoDTO dto) {
		Optional<Empleado> entiti = repositorio.findById(id);
		//cuerpo del metodo
		Empleado nuevoEmpleado = entiti.get();
		nuevoEmpleado.setId(dto.getId());
		nuevoEmpleado.setApellido(dto.getApellido());
		nuevoEmpleado.setDni(dto.getDni());
		nuevoEmpleado.setEmail(dto.getEmail());
		nuevoEmpleado.setEsCliente(false);
		nuevoEmpleado.setFechaNacimiento(dto.getFechaNacimiento());
		nuevoEmpleado.setImagen(dto.getImagen());
		nuevoEmpleado.setNombre(dto.getNombre());
		nuevoEmpleado.setPassword(dto.getPassword());
		nuevoEmpleado.setRol(dto.getRol());
		nuevoEmpleado.setTelefono(dto.getTelefono());
		nuevoEmpleado = repositorio.save(nuevoEmpleado);
		dto.setId(nuevoEmpleado.getId());
		return dto;
	}
	//delete
	@Transactional
	public boolean delete(long id) {
		if(repositorio.existsById(id)) {
			repositorio.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
}
