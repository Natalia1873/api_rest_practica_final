package com.keepcoding.api_rest_practica_final.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ClienteController {
	
	@Autowired
    private ClienteService service;

	
	@GetMapping
    public ResponseEntity<?>  index() {
		List<Cliente> lista = service.allClientes();
		
		Map<String,String> response = new HashMap<>();
		
		if(lista.size()>0) {
			return ResponseEntity.ok(service.allClientes());
		}else {
			response.put("mensaje", "No hay registros en este momento");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
    

	@GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente findCliente=service.clienteById(id);
		Map<String,String> response = new HashMap<>();
		
		if(findCliente != null) {
			return ResponseEntity.ok(findCliente);
		}else {
			response.put("mensaje", "No hay registro con este identificador id="+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}		
		
	}

	@PostMapping
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
		Cliente clienteNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			clienteNew = service.clienteSave(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","El Cliente ha sido creado con exito!");
		response.put("cliente", clienteNew);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteUpdate = null;
		Map<String,Object> response = new HashMap<>();
		Cliente editCliente = service.clienteById(id);
		
		if(editCliente==null) {
			response.put("mensaje","No existe el registro con id:"+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		try {
			
			editCliente.setApellido(cliente.getApellido());
			editCliente.setCodigoPostal(cliente.getCodigoPostal());
			editCliente.setCompras(cliente.getCompras());
			editCliente.setDireccion(cliente.getDireccion());
			editCliente.setEmpresa(cliente.getEmpresa());
			editCliente.setFechaNacimiento(cliente.getFechaNacimiento());
			editCliente.setNombre(cliente.getNombre());
			editCliente.setProvincia(cliente.getProvincia());
			editCliente.setPuesto(cliente.getPuesto());
			editCliente.setTelefono(cliente.getTelefono());
			
			
			clienteUpdate= service.clienteSave(editCliente);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","La Cliente ha sido actualizado con exito");
		response.put("cliente", clienteUpdate);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
				
	}
	
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Cliente clienteDrop=null;
		Map<String,Object> response = new HashMap<>();
		
		clienteDrop = service.clienteById(id);
		
		if(clienteDrop==null) {
			response.put("mensaje", "No existe el registro con id:"+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		try {
			service.clienteDelete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar el Cliente en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","El Cliente ha sido borrado con éxito!");
		response.put("cliente", clienteDrop);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
