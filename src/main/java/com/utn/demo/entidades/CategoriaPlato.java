package com.utn.demo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CategoriaPlato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	@OneToMany(mappedBy = "categoriaPlato")
	private List<Plato> platos;
	
	@OneToMany(mappedBy = "categoriaMaestra")
	private List<SubcategoriaPlato> subcategorias_plato;
}
