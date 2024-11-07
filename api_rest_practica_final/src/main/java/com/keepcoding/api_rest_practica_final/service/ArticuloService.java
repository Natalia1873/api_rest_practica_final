package com.keepcoding.api_rest_practica_final.service;

import java.util.List;

import com.keepcoding.api_rest_practica_final.entity.Articulo;

public interface ArticuloService {
	
	public List<Articulo> allArticulos();
	
	public Articulo articuloById(Long id);

	public Articulo articuloSave(Articulo articulo);
	
	public void articuloDelete(Long id);
	
}