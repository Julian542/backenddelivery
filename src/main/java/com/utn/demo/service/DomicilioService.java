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
import com.utn.demo.entity.Localidad;
import com.utn.demo.entity.Usuario;
import com.utn.demo.repository.DomicilioRepository;

@Service
public class DomicilioService {

	protected final DomicilioRepository repo;

	public DomicilioService(DomicilioRepository repo) {
		this.repo = repo;
	}

	@Transactional
	public List<DomicilioDTO> buscarporUsuario(int id) {
		List<Domicilio> entidades = repo.buscarPorUsuario(id);
		List<DomicilioDTO> dtos = new ArrayList<>();
		try {
			for (Domicilio d : entidades) {

				DomicilioDTO unDto = new DomicilioDTO();
				unDto.setId(d.getId());
				unDto.setDepartamento(d.getDepartamento());
				unDto.setNumero(d.getNumero());
				unDto.setPiso(d.getPiso());
				unDto.setCalle(d.getCalle());
				unDto.setEliminado(d.isEliminado());

				LocalidadDTO localidadto = new LocalidadDTO();
				localidadto.setId(d.getLocalidad().getId());
				localidadto.setNombre(d.getLocalidad().getNombre());
				localidadto.setEliminado(d.isEliminado());
				unDto.setLocalidad(localidadto);

				UsuarioDTO user = new UsuarioDTO();
				user.setId(d.getPropietario().getId());
				user.setNombre(d.getPropietario().getNombre());
				user.setApellido(d.getPropietario().getApellido());
				user.setEliminado(d.isEliminado());
				unDto.setPropietario(user);
				dtos.add(unDto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dtos;
	}

	// getAll
	@Transactional
	public List<DomicilioDTO> findAll() {

		List<Domicilio> entidades = repo.findAllMod();
		List<DomicilioDTO> dtos = new ArrayList<>();
		try {
			for (Domicilio d : entidades) {
				DomicilioDTO unDto = new DomicilioDTO();
				unDto.setId(d.getId());
				unDto.setDepartamento(d.getDepartamento());
				unDto.setNumero(d.getNumero());
				unDto.setPiso(d.getPiso());
				unDto.setCalle(d.getCalle());
				unDto.setEliminado(d.isEliminado());

				LocalidadDTO localidadto = new LocalidadDTO();
				localidadto.setId(d.getLocalidad().getId());
				localidadto.setNombre(d.getLocalidad().getNombre());
				unDto.setEliminado(d.isEliminado());
				unDto.setLocalidad(localidadto);

				UsuarioDTO user = new UsuarioDTO();
				user.setId(d.getPropietario().getId());
				user.setNombre(d.getPropietario().getNombre());
				user.setApellido(d.getPropietario().getApellido());
				unDto.setPropietario(user);

				dtos.add(unDto);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dtos;
	}

	// getOne
	@Transactional
	public DomicilioDTO findById(int id) {

		Domicilio d = repo.findByIdMod(id);
		DomicilioDTO unDto = new DomicilioDTO();
		try {
			unDto.setId(d.getId());
			unDto.setDepartamento(d.getDepartamento());
			unDto.setNumero(d.getNumero());
			unDto.setPiso(d.getPiso());
			unDto.setCalle(d.getCalle());
			unDto.setEliminado(d.isEliminado());

			LocalidadDTO localidadto = new LocalidadDTO();
			localidadto.setId(d.getLocalidad().getId());
			localidadto.setNombre(d.getLocalidad().getNombre());
			unDto.setLocalidad(localidadto);

			UsuarioDTO user = new UsuarioDTO();
			user.setId(d.getPropietario().getId());
			user.setNombre(d.getPropietario().getNombre());
			user.setApellido(d.getPropietario().getApellido());

			unDto.setPropietario(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return unDto;
	}

	// save
	@Transactional
	public DomicilioDTO save(DomicilioDTO dto) { // PARA DAR DE ALTA UN DOMICILIO ENVIAR EL CAMPO ESCLIENTE BOOLEAN EN
		try { // EL JSON
			Domicilio dom = new Domicilio();

			dom.setCalle(dto.getCalle());
			dom.setDepartamento(dto.getDepartamento());
			dom.setNumero(dto.getNumero());
			dom.setPiso(dto.getPiso());
			dom.setEliminado(dto.isEliminado());

			Usuario usuario = new Usuario();
			usuario.setId(dto.getPropietario().getId());
			dom.setPropietario(usuario);

			Localidad loc = new Localidad();
			loc.setId(dto.getLocalidad().getId());
			loc.setNombre(dto.getLocalidad().getNombre());
			dom.setLocalidad(loc);

			dom = repo.save(dom);
			dto.setId(dom.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}

	// update
	@Transactional
	public DomicilioDTO update(int id, DomicilioDTO dto) {
		try {
			Optional<Domicilio> op = repo.findById(id);
			Domicilio dom = op.get();

			dom.setId(dto.getId());
			dom.setCalle(dto.getCalle());
			dom.setDepartamento(dto.getDepartamento());
			dom.setNumero(dto.getNumero());
			dom.setPiso(dto.getPiso());
			dom.setEliminado(dto.isEliminado());

			Usuario usuario = new Usuario();
			usuario.setId(dto.getPropietario().getId());
			dom.setPropietario(usuario);

			Localidad loc = new Localidad();
			loc.setId(dto.getLocalidad().getId());
			loc.setNombre(dto.getLocalidad().getNombre());
			dom.setLocalidad(loc);

			dom = repo.save(dom);
			dto.setId(dom.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}

	public boolean delete(int id) {
		try {
			repo.deleteDomicilioById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
