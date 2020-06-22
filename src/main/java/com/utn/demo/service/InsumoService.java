package com.utn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.utn.demo.dtos.CategoriaInsumoDTO;
import com.utn.demo.dtos.InsumoDTO;
import com.utn.demo.dtos.SubcategoriaInsumoDTO;
import com.utn.demo.dtos.UnidadMedidaDTO;
import com.utn.demo.entity.CategoriaInsumo;
import com.utn.demo.entity.Insumo;
import com.utn.demo.entity.SubcategoriaInsumo;
import com.utn.demo.entity.UnidadMedida;
import com.utn.demo.repository.InsumoRepository;

@Service
public class InsumoService {

	private InsumoRepository insumoRepository;

	public InsumoService(InsumoRepository insumoRepository) {
		this.insumoRepository = insumoRepository;
	}

	/**
	 * This method transform all entities 'Articulo' in 'ArticuloDTO' in the
	 * database
	 * 
	 * @return entities 'Articulo' transformed in 'Articulo'DTO (Data transference
	 *         Object)
	 * @since 1.0
	 */
	public List<InsumoDTO> getAll() {

		List<InsumoDTO> result = new ArrayList<>();

		for (Insumo object2 : insumoRepository.findAll()) {

			InsumoDTO object = new InsumoDTO();

			object.setId(object2.getId());
			object.setPrecioCompra(object2.getPrecioCompra());
			object.setPrecioVenta(object2.getPrecioVenta());
			object.setDescripcion(object2.getDescripcion());
			object.setStockActual(object2.getStockActual());
			object.setStockMinimo(object2.getStockMinimo());
			object.setStockMaximo(object2.getStockMaximo());
			object.setEsIngrediente(object2.isEsIngrediente());
			object.setImagen(object2.getImagen());

			try {

				UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
				unidadMedida.setId(object2.getUnidadMedida().getId());

				object.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				CategoriaInsumoDTO insumoCategoria = new CategoriaInsumoDTO();
				insumoCategoria.setId(object2.getCategoriaInsumo().getId());
				object.setCategoriaInsumo(insumoCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				SubcategoriaInsumoDTO insumoSubcategoria = new SubcategoriaInsumoDTO();
				insumoSubcategoria.setId(object2.getSubcategoriaInsumo().getId());
				object.setSubcategoriaInsumo(insumoSubcategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			result.add(object);
		}

		return result;

	}

	/**
	 * This method transform all entities 'Articulo' in 'ArticuloDTO' in the
	 * database
	 * 
	 * @return entities 'Articulo' transformed in 'Articulo'DTO (Data transference
	 *         Object)
	 * @since 1.0
	 */
	/*
	 * public List<ArticuloDTO> getAllNoInsumos(boolean esInsumo) {
	 * 
	 * List<ArticuloDTO> result = new ArrayList<>();
	 * 
	 * for(Articulo object2 : articuloRepository.getAllNoInsumos(esInsumo)){
	 * ArticuloDTO object = new ArticuloDTO(); object.setId(object2.getId());
	 * object.setNombre(object2.getNombre());
	 * object.setDescripcion(object2.getDescripcion());
	 * object.setPrecioCompra(object2.getPrecioCompra());
	 * object.setStock(object2.getStock());
	 * object.setStockMinimo(object2.getStockMinimo());
	 * object.setStockMaximo(object2.getStockMaximo());
	 * object.setEsInsumo(object2.getEsInsumo());
	 * object.setPrecioVenta(object2.getPrecioVenta());
	 * 
	 * try { ArticuloCategoriaDTO articuloCategoria = new ArticuloCategoriaDTO();
	 * articuloCategoria.setId(object2.getCategoria().getId());
	 * articuloCategoria.setNombre(object2.getCategoria().getNombre());
	 * articuloCategoria.setDescripcion(object2.getCategoria().getDescripcion());
	 * object.setCategoria(articuloCategoria);
	 * 
	 * } catch(Exception e){ System.out.println(e.getMessage()); }
	 * 
	 * try { UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
	 * unidadMedida.setId(object2.getUnidadMedida().getId());
	 * unidadMedida.setNombre(object2.getUnidadMedida().getNombre());
	 * unidadMedida.setAbreviatura(object2.getUnidadMedida().getAbreviatura());
	 * object.setUnidadMedida(unidadMedida);
	 * 
	 * } catch(Exception e){ System.out.println(e.getMessage()); }
	 * 
	 * try { ImagenDTO imagen = new ImagenDTO();
	 * imagen.setId(object2.getImagen().getId());
	 * imagen.setUrl(object2.getImagen().getUrl()); object.setImagen(imagen);
	 * 
	 * } catch(Exception e){ System.out.println(e.getMessage()); }
	 * 
	 * 
	 * 
	 * result.add(object); }
	 * 
	 * return result;
	 * 
	 * } This method transform an entity 'Articulo' in 'ArticuloDTO' in the database
	 * 
	 * @return entity 'Articulo' transformed in 'ArticuloDTO' (Data transference
	 * Object)
	 * 
	 * @since 1.0
	 */

	public InsumoDTO getOne(long id) {

		Optional<Insumo> Optional = insumoRepository.findById(id);
		InsumoDTO object = new InsumoDTO();

		try {

			Insumo object2 = Optional.get();
			object.setId(object2.getId());
			object.setDescripcion(object2.getDescripcion());
			object.setPrecioCompra(object2.getPrecioCompra());
			object.setPrecioVenta(object2.getPrecioVenta());
			object.setStockActual(object2.getStockActual());
			object.setStockMinimo(object2.getStockMinimo());
			object.setStockMaximo(object2.getStockMaximo());
			object.setEsIngrediente(object2.isEsIngrediente());
			object.setImagen(object2.getImagen());

			try {

				CategoriaInsumoDTO insumoCategoria = new CategoriaInsumoDTO();
				insumoCategoria.setId(object2.getCategoriaInsumo().getId());
				object.setCategoriaInsumo(insumoCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				UnidadMedidaDTO unidadMedida = new UnidadMedidaDTO();
				unidadMedida.setId(object2.getUnidadMedida().getId());
				object.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				SubcategoriaInsumoDTO insumoSubcategoria = new SubcategoriaInsumoDTO();
				insumoSubcategoria.setId(object2.getSubcategoriaInsumo().getId());
				object.setSubcategoriaInsumo(insumoSubcategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {

			System.out.println("No existe el id");

		}

		return object;

	}

	/**
	 * This method transform an entity 'ArticuloDTO' in the entity 'Articulo' for
	 * save it in the database
	 * 
	 * @param articuloDTO it is an object DTO that comes from controller that will
	 *                    transformed in an entity
	 * @return entities 'ArticuloDTO'.
	 * @since 1.0
	 */
	public InsumoDTO save(InsumoDTO insumoDTO) {

		Insumo insumo = new Insumo();

		insumo.setDescripcion(insumoDTO.getDescripcion());
		insumo.setPrecioCompra(insumoDTO.getPrecioCompra());
		insumo.setPrecioVenta(insumoDTO.getPrecioVenta());
		insumo.setStockActual(insumoDTO.getStockActual());
		insumo.setStockMinimo(insumoDTO.getStockMinimo());
		insumo.setStockMaximo(insumoDTO.getStockMaximo());
		insumo.setEsIngrediente(insumoDTO.isEsIngrediente());
		insumo.setImagen(insumoDTO.getImagen());

		try {

			CategoriaInsumo insumoCategoria = new CategoriaInsumo();
			insumoCategoria.setId(insumoDTO.getCategoriaInsumo().getId());
			insumo.setCategoriaInsumo(insumoCategoria);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			UnidadMedida unidadMedida = new UnidadMedida();
			unidadMedida.setId(insumoDTO.getUnidadMedida().getId());
			insumo.setUnidadMedida(unidadMedida);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {

			SubcategoriaInsumo subcategoriaInsumo = new SubcategoriaInsumo();
			subcategoriaInsumo.setId(insumoDTO.getSubcategoriaInsumo().getId());
			insumo.setSubcategoriaInsumo(subcategoriaInsumo);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		insumoRepository.save(insumo);

		insumoDTO.setId(insumo.getId());
		return insumoDTO;

	}

	/**
	 * This method transform an entity 'ArticuloDTO' in the entity 'Articulo' for
	 * update it in the database
	 * 
	 * @param articuloDTO it is an object DTO that will transformed.
	 * @param id          it is an id that corresponds with the index of the entity
	 *                    in the DB.
	 * @return entities 'ArticuloDTO'.
	 * @since 1.0
	 */
	public InsumoDTO update(InsumoDTO insumoDTO, long id) {

		Optional<Insumo> optional = insumoRepository.findById(id);
		Insumo insumo = new Insumo();

		try {

			insumo = optional.get();

			insumo.setDescripcion(insumoDTO.getDescripcion());
			insumo.setPrecioCompra(insumoDTO.getPrecioCompra());
			insumo.setPrecioVenta(insumoDTO.getPrecioVenta());
			insumo.setStockMinimo(insumoDTO.getStockMinimo());
			insumo.setStockActual(insumoDTO.getStockActual());
			insumo.setStockMaximo(insumoDTO.getStockMaximo());
			insumo.setEsIngrediente(insumoDTO.isEsIngrediente());
			insumo.setImagen(insumoDTO.getImagen());

			try {

				CategoriaInsumo insumoCategoria = new CategoriaInsumo();
				insumoCategoria.setId(insumoDTO.getCategoriaInsumo().getId());
				insumo.setCategoriaInsumo(insumoCategoria);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			try {

				UnidadMedida unidadMedida = new UnidadMedida();
				unidadMedida.setId(insumoDTO.getUnidadMedida().getId());
				insumo.setUnidadMedida(unidadMedida);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			insumoRepository.save(insumo);

			insumoDTO.setId(insumo.getId());

		} catch (Exception e) {

			System.out.println("Bad Request");
			insumoDTO.setId(0);

		}

		return insumoDTO;

	}

	/**
	 * This method call the repository for delete an entity 'Articulo' in the
	 * database for the id.
	 * 
	 * @param id it is an id that match with the index of the entity in the DB.
	 * @return true in the case of being successful, or false in the opposite case.
	 * @since 1.0
	 */
	public boolean delete(long id) {

		try {

			insumoRepository.deleteById(id);

		} catch (Exception e) {
			return false;
		}
		return true;

	}

}