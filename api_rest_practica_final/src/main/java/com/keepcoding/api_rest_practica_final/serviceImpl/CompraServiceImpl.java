package com.keepcoding.api_rest_practica_final.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepcoding.api_rest_practica_final.entity.Articulo;
import com.keepcoding.api_rest_practica_final.entity.Cliente;
import com.keepcoding.api_rest_practica_final.entity.Compra;
import com.keepcoding.api_rest_practica_final.repository.CompraRepository;
import com.keepcoding.api_rest_practica_final.service.CompraService;

@Service
public class CompraServiceImpl implements CompraService{

	@Autowired
	private CompraRepository compraRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Compra> allCompra() {
		return (List<Compra>) compraRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Compra compraById(Long id) {
		return compraRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Compra compraSave(Compra compra) {
		return compraRepository.save(compra);
	}

	@Override
	@Transactional
	public void customerDelete(Long id) {
		compraRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> allClientes() {
		return compraRepository.findAllClientes();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> allArticulos() {
		return compraRepository.findAllArticulos();
	}
	

}
