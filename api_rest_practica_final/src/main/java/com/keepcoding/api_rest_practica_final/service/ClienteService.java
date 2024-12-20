package com.keepcoding.api_rest_practica_final.service;

import java.util.List;

import com.keepcoding.api_rest_practica_final.entity.Cliente;

public interface ClienteService {
	
	public List<Cliente> allClientes();
	
	public Cliente clienteById(Long id);

	public Cliente clienteSave(Cliente cliente);
	
	public void clienteDelete(Long id);

}


