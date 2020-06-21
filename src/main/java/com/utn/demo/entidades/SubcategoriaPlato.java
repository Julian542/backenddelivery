package com.utn.demo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SubcategoriaPlato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	
	@OneToMany(mappedBy = "subcategoriaPlato")
	private List<Plato> platos;
	
	@ManyToOne
	private CategoriaPlato categoriaMaestra;
}
