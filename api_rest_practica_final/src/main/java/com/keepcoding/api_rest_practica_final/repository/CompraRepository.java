package com.keepcoding.api_rest_practica_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keepcoding.api_rest_practica_final.entity.Articulo;
import com.keepcoding.api_rest_practica_final.entity.Cliente;
import com.keepcoding.api_rest_practica_final.entity.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long> {

	@Query("from Cliente")
	public List<Cliente> findAllClientes();

	@Query("from Articulo")
	public List<Articulo> findAllArticulos();

}
