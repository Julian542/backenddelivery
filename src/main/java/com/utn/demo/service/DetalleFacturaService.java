package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.DetalleFacturaDTO;
import com.utn.demo.entity.DetalleFactura;
import com.utn.demo.entity.Factura;
import com.utn.demo.repository.DetalleFacturaRepository;
import com.utn.demo.repository.FacturaRepository;

@Service
public class DetalleFacturaService {

	protected final DetalleFacturaRepository repositoryDetalleFactura;
	protected final FacturaRepository repositoryFactura;
	
	public DetalleFacturaService(DetalleFacturaRepository repository,FacturaRepository repositoryFactura) {
		this.repositoryDetalleFactura = repository;
		this.repositoryFactura = repositoryFactura;
	}
	
	@Transactional
	public List<DetalleFacturaDTO> findAll() throws Exception{
		List<DetalleFactura> entities = repositoryDetalleFactura.findAll();
		List<DetalleFacturaDTO> dtos = new ArrayList<>();
		try {
			for( DetalleFactura d : entities) {
				DetalleFacturaDTO unDto = new DetalleFacturaDTO();
				unDto.setId(d.getId());
				unDto.setCantidad(d.getCantidad());
				unDto.setFacturaRelacionada(d.getFactura().getNumeroFactura());
				dtos.add(unDto);
			}
			return dtos;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	/*@Transactional
	public List<DetalleFacturaDTO> buscarPorIdP(long id ) throws Exception{
		List<DetalleFactura> entities = repositoryDetalleFactura.buscarporIdP(id);
		List<DetalleFacturaDTO> dtos = new ArrayList<>();
		try {
			for( DetalleFactura d : entities) {
				DetalleFacturaDTO unDto = new DetalleFacturaDTO();
				unDto.setId(d.getId());
				unDto.setCantidad(d.getCantidad());
				unDto.setFacturaRelacionada(d.getFactura().getNumeroFactura());
				dtos.add(unDto);
			}
			return dtos;
		}catch(Exception e) {
			throw new Exception();
		}
	}*/
	
	@Transactional
	public DetalleFacturaDTO findById(long id) throws Exception{
		Optional<DetalleFactura> entityOptional = repositoryDetalleFactura.findById(id);
		DetalleFacturaDTO unDto = new DetalleFacturaDTO();
		try {
			DetalleFactura d = entityOptional.get();
			unDto.setId(d.getId());
			unDto.setCantidad(d.getCantidad());
			unDto.setFacturaRelacionada(d.getFactura().getNumeroFactura());
			return unDto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public DetalleFacturaDTO save(DetalleFacturaDTO dto) throws Exception{
		DetalleFactura entity = new DetalleFactura();
		entity.setCantidad(dto.getCantidad());
		
		Optional<Factura> Factura = repositoryFactura.findById(dto.getFacturaRelacionada());
		Factura relacion = Factura.get();
		entity.setFactura(relacion);
		
		try {
			entity = repositoryDetalleFactura.save(entity);
			dto.setId(entity.getId());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public DetalleFacturaDTO update(long id, DetalleFacturaDTO dto) throws Exception{
		Optional<DetalleFactura> entityOptional = repositoryDetalleFactura.findById(id);
		try {
			DetalleFactura entidad = entityOptional.get();
			entidad.setId(dto.getId());
			entidad.setCantidad(dto.getCantidad());
			Optional<Factura> Factura = repositoryFactura.findById(dto.getFacturaRelacionada());
			Factura relacion = Factura.get();
			entidad.setFactura(relacion);
			
			repositoryDetalleFactura.save(entidad);
			dto.setId(entidad.getId());
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public boolean delete(long id) throws Exception{
		try {
			if(repositoryDetalleFactura.existsById(id)) {
				repositoryDetalleFactura.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
