package com.utn.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.utn.demo.dtos.DomicilioDTO;
import com.utn.demo.dtos.LocalidadDTO;
import com.utn.demo.dtos.UsuarioDTO;
import com.utn.demo.entity.Domicilio;
import com.utn.demo.entity.Usuario;
import com.utn.demo.repository.DomicilioRepository;
import com.utn.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository repositorioUsuario;
	private DomicilioRepository repositorioDomicilio;

	public UsuarioService(UsuarioRepository repositorioUsuario, DomicilioRepository repositorioDomicilio) {
		this.repositorioUsuario = repositorioUsuario;
		this.repositorioDomicilio = repositorioDomicilio;
	}

	@Transactional
	public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {

		File archivo = new File("static\\images\\" + file.getOriginalFilename());
		if (archivo.exists()) {
			System.out.println("Ya existe esta imagen");
			return archivo.getAbsolutePath();
		} else {
			System.out.println("No existe esta imagen");
			file.transferTo(archivo);
			return archivo.getAbsolutePath();
		}
	}

	public List<UsuarioDTO> getCocineros() {

		List<UsuarioDTO> dtos = new ArrayList<>();

		for (Usuario e : repositorioUsuario.getCocineros()) {
			UsuarioDTO unDto = new UsuarioDTO();
			unDto.setId(e.getId());
			unDto.setApellido(e.getApellido());
			unDto.setDni(e.getDni());
			unDto.setEmail(e.getEmail());
			unDto.setEsCliente(e.isEsCliente());
			unDto.setFechaNacimiento(e.getFechaNacimiento());
			unDto.setNombre(e.getNombre());
			unDto.setPassword(e.getPassword());
			unDto.setRol(e.getRol());
			unDto.setTelefono(e.getTelefono());
			unDto.setEliminado(e.isEliminado());
			dtos.add(unDto);
		}
		return dtos;
	}

	// este metodo existe para consultar la existencia de un usuario en la base de
	// datos,

	@Transactional
	public String existeEmail(String email) {

		String validacion = repositorioUsuario.existeEmail(email);
		if (validacion != "0") {
			return "true";
		} else {
			return "false";
		}
	}

	//Traer todos los usuarios por rol
	@Transactional
	public List<UsuarioDTO> findAllPorRol(String rol) {
		List<UsuarioDTO> dtos = new ArrayList<>();

		for (Usuario e : repositorioUsuario.traerPorRol(rol)) {
			UsuarioDTO unDto = new UsuarioDTO();
			unDto.setId(e.getId());
			unDto.setApellido(e.getApellido());
			unDto.setDni(e.getDni());
			List<DomicilioDTO> domiciliosdto = new ArrayList<DomicilioDTO>();
			for (Domicilio d : repositorioDomicilio.buscarPorUsuario(e.getId())) {
				DomicilioDTO dtodom = new DomicilioDTO();
				dtodom.setCalle(d.getCalle());
				dtodom.setDepartamento(d.getDepartamento());
				dtodom.setId(d.getId());
				//
				LocalidadDTO localidaddto = new LocalidadDTO();
				localidaddto.setId(d.getLocalidad().getId());
				localidaddto.setNombre(d.getLocalidad().getNombre());
				localidaddto.setEliminado(d.getLocalidad().isEliminado());
				//
				dtodom.setLocalidad(localidaddto);
				dtodom.setNumero(d.getNumero());
				dtodom.setPiso(d.getPiso());
				dtodom.setEliminado(d.isEliminado());
				domiciliosdto.add(dtodom);
			}
			unDto.setDomicilios(domiciliosdto);
			unDto.setEmail(e.getEmail());
			unDto.setEsCliente(e.isEsCliente());
			unDto.setFechaNacimiento(e.getFechaNacimiento());
			unDto.setNombre(e.getNombre());
			unDto.setPassword(e.getPassword());
			unDto.setRol(e.getRol());
			unDto.setTelefono(e.getTelefono());
			unDto.setEliminado(e.isEliminado());
			dtos.add(unDto);
		}
		return dtos;
	}
	
	// findAll
	@Transactional
	public List<UsuarioDTO> findAll() {
		List<UsuarioDTO> dtos = new ArrayList<>();

		for (Usuario e : repositorioUsuario.findAllMod()) {
			UsuarioDTO unDto = new UsuarioDTO();
			unDto.setId(e.getId());
			unDto.setApellido(e.getApellido());
			unDto.setDni(e.getDni());
			List<DomicilioDTO> domiciliosdto = new ArrayList<DomicilioDTO>();
			for (Domicilio d : repositorioDomicilio.buscarPorUsuario(e.getId())) {
				DomicilioDTO dtodom = new DomicilioDTO();
				dtodom.setCalle(d.getCalle());
				dtodom.setDepartamento(d.getDepartamento());
				dtodom.setId(d.getId());
				//
				LocalidadDTO localidaddto = new LocalidadDTO();
				localidaddto.setId(d.getLocalidad().getId());
				localidaddto.setNombre(d.getLocalidad().getNombre());
				localidaddto.setEliminado(d.getLocalidad().isEliminado());
				//
				dtodom.setLocalidad(localidaddto);
				dtodom.setNumero(d.getNumero());
				dtodom.setPiso(d.getPiso());
				dtodom.setEliminado(d.isEliminado());
				domiciliosdto.add(dtodom);
			}
			unDto.setDomicilios(domiciliosdto);
			unDto.setEmail(e.getEmail());
			unDto.setEsCliente(e.isEsCliente());
			unDto.setFechaNacimiento(e.getFechaNacimiento());
			unDto.setNombre(e.getNombre());
			unDto.setPassword(e.getPassword());
			unDto.setRol(e.getRol());
			unDto.setTelefono(e.getTelefono());
			unDto.setEliminado(e.isEliminado());
			dtos.add(unDto);
		}
		return dtos;
	}

	@Transactional
	public UsuarioDTO findById(int id) {

		Usuario e = repositorioUsuario.findByIdMod(id);
		UsuarioDTO unDto = new UsuarioDTO();
		unDto.setId(e.getId());
		unDto.setApellido(e.getApellido());
		unDto.setDni(e.getDni());
		List<DomicilioDTO> domiciliosdto = new ArrayList<DomicilioDTO>();
		for (Domicilio d : repositorioDomicilio.buscarPorUsuario(e.getId())) {
			DomicilioDTO dtodom = new DomicilioDTO();
			dtodom.setCalle(d.getCalle());
			dtodom.setDepartamento(d.getDepartamento());
			dtodom.setId(d.getId());
			//
			LocalidadDTO localidaddto = new LocalidadDTO();
			localidaddto.setId(d.getLocalidad().getId());
			localidaddto.setNombre(d.getLocalidad().getNombre());
			localidaddto.setEliminado(d.getLocalidad().isEliminado());
			//
			dtodom.setLocalidad(localidaddto);
			dtodom.setNumero(d.getNumero());
			dtodom.setPiso(d.getPiso());
			dtodom.setEliminado(d.isEliminado());
			domiciliosdto.add(dtodom);
		}
		unDto.setDomicilios(domiciliosdto);
		unDto.setEmail(e.getEmail());
		unDto.setEsCliente(e.isEsCliente());
		unDto.setFechaNacimiento(e.getFechaNacimiento());
		unDto.setNombre(e.getNombre());
		unDto.setPassword(e.getPassword());
		unDto.setRol(e.getRol());
		unDto.setTelefono(e.getTelefono());
		unDto.setEliminado(e.isEliminado());

		return unDto;
	}

	@Transactional
	public UsuarioDTO buscarPorCorreo(String email) {

		Optional<Usuario> optionalentity = repositorioUsuario.buscarPorEmail(email);
		Usuario e = optionalentity.get();
		UsuarioDTO unDto = new UsuarioDTO();
		unDto.setId(e.getId());
		unDto.setApellido(e.getApellido());
		unDto.setDni(e.getDni());

		List<DomicilioDTO> domiciliosdto = new ArrayList<DomicilioDTO>();
		for (Domicilio d : repositorioDomicilio.buscarPorUsuario(e.getId())) {
			DomicilioDTO dtodom = new DomicilioDTO();
			dtodom.setCalle(d.getCalle());
			dtodom.setDepartamento(d.getDepartamento());
			dtodom.setId(d.getId());
			//
			LocalidadDTO localidaddto = new LocalidadDTO();
			localidaddto.setId(d.getLocalidad().getId());
			localidaddto.setNombre(d.getLocalidad().getNombre());
			localidaddto.setEliminado(d.getLocalidad().isEliminado());
			//
			dtodom.setLocalidad(localidaddto);
			dtodom.setNumero(d.getNumero());
			dtodom.setPiso(d.getPiso());
			dtodom.setEliminado(d.isEliminado());
			domiciliosdto.add(dtodom);
		}

		unDto.setDomicilios(domiciliosdto);
		unDto.setEmail(e.getEmail());
		unDto.setEsCliente(e.isEsCliente());
		unDto.setFechaNacimiento(e.getFechaNacimiento());
		unDto.setNombre(e.getNombre());
		unDto.setPassword(e.getPassword());
		unDto.setRol(e.getRol());
		unDto.setTelefono(e.getTelefono());
		unDto.setEliminado(e.isEliminado());

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
		u.setNombre(dto.getNombre());
		u.setPassword(dto.getPassword());
		u.setRol(dto.getRol());
		u.setTelefono(dto.getTelefono());
		u.setEliminado(dto.isEliminado());
		// termina proceso

		u = repositorioUsuario.save(u);
		dto.setId(u.getId());
		return dto;
	}

	@Transactional
	public UsuarioDTO update(int id, UsuarioDTO dto) {
		Optional<Usuario> entitiOpcional = repositorioUsuario.findById(id);
		Usuario u = entitiOpcional.get();

		u.setId(dto.getId());
		u.setApellido(dto.getApellido());
		u.setDni(dto.getDni());
		u.setEmail(dto.getEmail());
		u.setEsCliente(dto.isEsCliente());
		u.setFechaNacimiento(dto.getFechaNacimiento());
		u.setNombre(dto.getNombre());
		u.setPassword(dto.getPassword());
		u.setRol(dto.getRol());
		u.setTelefono(dto.getTelefono());
		u.setEliminado(dto.isEliminado());
		u = repositorioUsuario.save(u);
		dto.setId(u.getId());
		return dto;
	}

	public boolean delete(int id) {
		try {
			repositorioUsuario.deleteUsuarioById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
