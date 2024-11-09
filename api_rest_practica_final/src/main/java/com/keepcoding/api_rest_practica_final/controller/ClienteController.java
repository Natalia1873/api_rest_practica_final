package com.keepcoding.api_rest_practica_final.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.keepcoding.api_rest_practica_final.entity.Cliente;
import com.keepcoding.api_rest_practica_final.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<?> getAllClientes() {
        try {
            List<Cliente> clientes = service.allClientes();
            if (clientes != null && !clientes.isEmpty()) {
                return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "No hay clientes registrados");
                return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error al obtener los clientes: " + e.getMessage());
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id) {
        try {
            Cliente cliente = service.clienteById(id);
            if (cliente != null) {
                return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "Cliente no encontrado");
                return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error al obtener el cliente: " + e.getMessage());
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
        Cliente clienteNew = null;
        Map<String,Object> response = new HashMap<>();
        
        try {
            clienteNew = service.clienteSave(cliente);
            response.put("mensaje", "El Cliente ha sido creado con exito!");
            response.put("cliente", clienteNew);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar insert en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteActual = service.clienteById(id);
            
            if(clienteActual == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "No existe el cliente con id: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmpresa(cliente.getEmpresa());
            clienteActual.setPuesto(cliente.getPuesto());
            clienteActual.setDireccion(cliente.getDireccion());
            clienteActual.setCodigoPostal(cliente.getCodigoPostal());
            clienteActual.setProvincia(cliente.getProvincia());
            clienteActual.setTelefono(cliente.getTelefono());
            clienteActual.setFechaNacimiento(cliente.getFechaNacimiento());
            
            Cliente clienteUpdated = service.clienteSave(clienteActual);
            
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "El cliente ha sido actualizado con éxito");
            response.put("cliente", clienteUpdated);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al actualizar el cliente");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Cliente clienteDrop = null;
        Map<String,Object> response = new HashMap<>();
        
        clienteDrop = service.clienteById(id);
        if(clienteDrop == null) {
            response.put("mensaje", "No existe el registro con id:" + id);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        try {
            service.clienteDelete(id);
            response.put("mensaje", "El cliente ha sido borrado con éxito!");
            response.put("cliente", clienteDrop);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al borrar el Cliente en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
