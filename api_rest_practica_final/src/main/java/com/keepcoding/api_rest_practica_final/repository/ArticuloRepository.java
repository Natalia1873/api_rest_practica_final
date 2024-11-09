package com.keepcoding.api_rest_practica_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.keepcoding.api_rest_practica_final.entity.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

}
