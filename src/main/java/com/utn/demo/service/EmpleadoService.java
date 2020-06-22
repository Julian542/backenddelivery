package com.utn.demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

import com.utn.demo.dtos.EmpleadoDTO;
import com.utn.demo.repository.EmpleadoRepository;

@Service
public class EmpleadoService {
	
	protected final EmpleadoRepository repositorio;
	
	public EmpleadoService(EmpleadoRepository repositorio) {
		this.repositorio = repositorio;
	}
	
	public List<EmpleadoDTO> getAll(){
		
		List<EmpleadoDTO> dtos = new ArrayList();
		return dtos;
		
	}
}
