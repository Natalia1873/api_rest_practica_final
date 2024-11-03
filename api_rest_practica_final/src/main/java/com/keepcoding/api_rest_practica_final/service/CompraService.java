package com.keepcoding.api_rest_practica_final.service;

import java.util.List;

import com.keepcoding.api_rest_practica_final.entity.Articulo;
import com.keepcoding.api_rest_practica_final.entity.Cliente;
import com.keepcoding.api_rest_practica_final.entity.Compra;

public interface CompraService {
	
	public List<Compra> allCompra();
	
	public Compra compraById(Long id);

	public Compra compraSave(Compra compra);
	
	public void customerDelete(Long id);
	
	public List<Cliente> allClientes();
	
	public List<Articulo> allArticulos();
	
	

}

