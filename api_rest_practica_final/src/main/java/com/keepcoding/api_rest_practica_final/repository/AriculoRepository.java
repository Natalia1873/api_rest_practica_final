package com.keepcoding.api_rest_practica_final.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.keepcoding.api_rest_practica_final.entity.Articulo;

@Repository
public interface AriculoRepository extends CrudRepository<Articulo, Long> {

}
