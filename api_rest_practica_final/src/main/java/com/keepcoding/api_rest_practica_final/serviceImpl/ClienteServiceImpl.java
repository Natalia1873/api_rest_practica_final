package com.keepcoding.api_rest_practica_final.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.keepcoding.api_rest_practica_final.entity.Cliente;
import com.keepcoding.api_rest_practica_final.repository.ClienteRepository;
import com.keepcoding.api_rest_practica_final.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> allClientes() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            System.out.println("Número de clientes encontrados: " + clientes.size());
            return clientes;
        } catch (Exception e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente clienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public Cliente clienteSave(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void clienteDelete(Long id) {
        clienteRepository.deleteById(id);
    }
}
