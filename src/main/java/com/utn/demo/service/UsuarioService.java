package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.DomicilioDTO;
import com.utn.demo.dtos.LocalidadDTO;
import com.utn.demo.dtos.UsuarioDTO;
import com.utn.demo.entity.Domicilio;
import com.utn.demo.entity.Usuario;
import com.utn.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	protected final UsuarioRepository repositorio;

	public UsuarioService(UsuarioRepository repositorio) {
		this.repositorio = repositorio;
	}

	@Transactional
	public String existeEmail(String email) {

		String validacion = repositorio.existeEmail(email);
		if (validacion != "0") {
			return "true";
		} else {
			return "false";
		}
	}
	// este metodo existe para consultar la existencia de un usuario en la base de
	// datos,

	// findAll
	@Transactional
	public List<UsuarioDTO> findAll() {
		List<UsuarioDTO> dtos = new ArrayList<>();

		for (Usuario e : repositorio.findAll()) {
			UsuarioDTO unDto = new UsuarioDTO();
			unDto.setId(e.getId());
			unDto.setApellido(e.getApellido());
			unDto.setDni(e.getDni());
			List<DomicilioDTO> domiciliosdto = new ArrayList();
			for (Domicilio d : e.getDomicilios()) {
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

	@Transactional
	public UsuarioDTO findById(int id) {

		Optional<Usuario> optionalentity = repositorio.findById(id);
		Usuario e = optionalentity.get();
		UsuarioDTO unDto = new UsuarioDTO();
		unDto.setId(e.getId());
		unDto.setApellido(e.getApellido());
		unDto.setDni(e.getDni());
		List<DomicilioDTO> domiciliosdto = new ArrayList();
		for (Domicilio d : e.getDomicilios()) {
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

		return unDto;
	}

	@Transactional
	public UsuarioDTO buscarPorEmail(String email) {

		Optional<Usuario> optionalentity = repositorio.buscarPorEmail(email);
		Usuario e = optionalentity.get();
		UsuarioDTO unDto = new UsuarioDTO();
		unDto.setId(e.getId());
		unDto.setApellido(e.getApellido());
		unDto.setDni(e.getDni());
		List<DomicilioDTO> domiciliosdto = new ArrayList();
		for (Domicilio d : e.getDomicilios()) {
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

		return unDto;
	}

	@Transactional
	public UsuarioDTO save(UsuarioDTO dto) {
		Usuario u = new Usuario();

		// empieza proceso
		u.setApellido(dto.getApellido());
		u.setDni(dto.getDni());
		u.setEmail(dto.getEmail());
		u.setEsCliente(dto.isEsCliente());
		u.setFechaNacimiento(dto.getFechaNacimiento());
		u.setImagen(dto.getImagen());
		u.setNombre(dto.getNombre());
		u.setPassword(dto.getPassword());
		u.setRol(dto.getRol());
		u.setTelefono(dto.getTelefono());
		// termina proceso

		u = repositorio.save(u);
		dto.setId(u.getId());
		return dto;
	}

	@Transactional
	public UsuarioDTO update(int id, UsuarioDTO dto) {
		Optional<Usuario> entitiOpcional = repositorio.findById(id);
		Usuario u = entitiOpcional.get();

		u.setId(dto.getId());
		u.setApellido(dto.getApellido());
		u.setDni(dto.getDni());
		u.setEmail(dto.getEmail());
		u.setEsCliente(dto.isEsCliente());
		u.setFechaNacimiento(dto.getFechaNacimiento());
		u.setImagen(dto.getImagen());
		u.setNombre(dto.getNombre());
		u.setPassword(dto.getPassword());
		u.setRol(dto.getRol());
		u.setTelefono(dto.getTelefono());
		u = repositorio.save(u);
		dto.setId(u.getId());
		return dto;
	}

	public boolean delete(int id) {
		try {
			repositorio.deleteUsuarioById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
