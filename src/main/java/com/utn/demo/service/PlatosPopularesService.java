package com.utn.demo.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.stereotype.Service;
import com.utn.demo.dtos.CategoriaPlatoDTO;
import com.utn.demo.dtos.DetallePlatoDTO;
import com.utn.demo.dtos.PlatoDTO;
import com.utn.demo.dtos.PlatosPopularesDTO;
import com.utn.demo.dtos.SubcategoriaPlatoDTO;
import com.utn.demo.entity.DetallePlato;
import com.utn.demo.entity.Plato;
import com.utn.demo.entity.PlatosPopulares;
import com.utn.demo.repository.PlatosPopularesRepository;

@Service
public class PlatosPopularesService {

	private PlatosPopularesRepository PlatosPopularesRepository;

	public PlatosPopularesService(PlatosPopularesRepository platosPopularesRepository) {
		PlatosPopularesRepository = platosPopularesRepository;
	}

	public PlatosPopularesDTO getOne() throws Exception {
		PlatosPopularesDTO dto = new PlatosPopularesDTO();
		PlatosPopulares entity = new PlatosPopulares();
		try {
			List<Plato> platos = PlatosPopularesRepository.platosPopulares();
			GregorianCalendar date = new GregorianCalendar();
			entity.setFecha(date.getTime());
			entity.setPlatos(platos);
			entity = PlatosPopularesRepository.save(entity);

			dto.setId(entity.getId());
			dto.setFecha(entity.getFecha());
			List<PlatoDTO> dtoss = new ArrayList<>();
			for (Plato plat : entity.getPlatos()) {
				PlatoDTO platosDTO = new PlatoDTO();
				platosDTO.setId(plat.getId());
				platosDTO.setCantidadVendida(plat.getCantidadVendida());
				platosDTO.setDescripcion(plat.getDescripcion());
				platosDTO.setImagen(plat.getImagen());
				platosDTO.setNombre(plat.getNombre());
				platosDTO.setPrecioCosto(plat.getPrecioCosto());
				platosDTO.setPrecioVenta(plat.getPrecioVenta());
				platosDTO.setTiempoPreparacion(plat.getTiempoPreparacion());

				CategoriaPlatoDTO categoriaPlatoDTO = new CategoriaPlatoDTO();
				categoriaPlatoDTO.setId(plat.getCategoriaPlato().getId());
				categoriaPlatoDTO.setNombre(plat.getCategoriaPlato().getNombre());
				platosDTO.setCategoriaPlato(categoriaPlatoDTO);

				List<DetallePlatoDTO> recetaDTO = new ArrayList<>();
				for (DetallePlato detPlato : plat.getReceta()) {
					DetallePlatoDTO detPlatoDTO = new DetallePlatoDTO();
					detPlatoDTO.setId(detPlato.getId());
					recetaDTO.add(detPlatoDTO);
				}
				platosDTO.setReceta(recetaDTO);

				SubcategoriaPlatoDTO subcategoriaPlatoDTO = new SubcategoriaPlatoDTO();
				subcategoriaPlatoDTO.setId(plat.getSubcategoriaPlato().getId());
				subcategoriaPlatoDTO.setNombre(plat.getSubcategoriaPlato().getNombre());
				platosDTO.setSubcategoriaPlato(subcategoriaPlatoDTO);

				dtoss.add(platosDTO);
			}
			dto.setPlato(dtoss);
		} catch (Exception e) {
			throw new Exception();
		}
		return dto;
	}

	public List<PlatosPopularesDTO> getAll() throws Exception {
		try {
			List<PlatosPopularesDTO> platosPopularesDTOs = new ArrayList<>();
			for (PlatosPopulares platosPopulares : PlatosPopularesRepository.findAll()) {
				PlatosPopularesDTO platosPopularesDTO = new PlatosPopularesDTO();
				platosPopularesDTO.setId(platosPopulares.getId());
				platosPopularesDTO.setFecha(platosPopulares.getFecha());

				List<PlatoDTO> platoDTOs = new ArrayList<>();
				for (Plato plat : platosPopulares.getPlatos()) {
					PlatoDTO platosDTO = new PlatoDTO();
					platosDTO.setId(plat.getId());
					platosDTO.setCantidadVendida(plat.getCantidadVendida());
					platosDTO.setDescripcion(plat.getDescripcion());
					platosDTO.setImagen(plat.getImagen());
					platosDTO.setNombre(plat.getNombre());
					platosDTO.setPrecioCosto(plat.getPrecioCosto());
					platosDTO.setPrecioVenta(plat.getPrecioVenta());
					platosDTO.setTiempoPreparacion(plat.getTiempoPreparacion());

					CategoriaPlatoDTO categoriaPlatoDTO = new CategoriaPlatoDTO();
					categoriaPlatoDTO.setId(plat.getCategoriaPlato().getId());
					categoriaPlatoDTO.setNombre(plat.getCategoriaPlato().getNombre());
					platosDTO.setCategoriaPlato(categoriaPlatoDTO);

					List<DetallePlatoDTO> recetaDTO = new ArrayList<>();
					for (DetallePlato detPlato : plat.getReceta()) {
						DetallePlatoDTO detPlatoDTO = new DetallePlatoDTO();
						detPlatoDTO.setId(detPlato.getId());
						recetaDTO.add(detPlatoDTO);
					}
					platosDTO.setReceta(recetaDTO);

					SubcategoriaPlatoDTO subcategoriaPlatoDTO = new SubcategoriaPlatoDTO();
					subcategoriaPlatoDTO.setId(plat.getSubcategoriaPlato().getId());
					subcategoriaPlatoDTO.setNombre(plat.getSubcategoriaPlato().getNombre());
					platosDTO.setSubcategoriaPlato(subcategoriaPlatoDTO);

					platoDTOs.add(platosDTO);
				}

				platosPopularesDTO.setPlato(platoDTOs);
				platosPopularesDTOs.add(platosPopularesDTO);
			}
			return platosPopularesDTOs;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
