package com.vics.backendapirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vics.backendapirest.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long>{

//	@Query("SELECT p FROM product WHERE p.name LIKE %?1%")
//	public List<Product> filterByName(String name);
	
	// Esto es un método que gracias a Spring, el Containing hace lo mismo que el método
	// anterior. Con el IgnoreCase ignora si es mayúscula o minúscula.
	// Esto se puede buscar en las proyectos de Spring, en Spring Data, en Learn,
	// en la última versión estable, en Query Creation.
	public List<Product> findByNameContainingIgnoreCase(String name);
}
