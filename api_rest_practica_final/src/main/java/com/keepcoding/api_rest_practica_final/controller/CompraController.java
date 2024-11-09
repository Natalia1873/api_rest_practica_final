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
import com.keepcoding.api_rest_practica_final.entity.Compra;
import com.keepcoding.api_rest_practica_final.service.CompraService;


@RestController
@RequestMapping("/api/compras")
public class CompraController {
	
	@Autowired
    private CompraService service;

    @GetMapping
    public ResponseEntity<?>  index() {
		List<Compra> lista = service.allCompras();
		
		Map<String,String> response = new HashMap<>();
		
		if(lista.size()>0) {
			return ResponseEntity.ok(service.allCompras());
		}else {
			response.put("mensaje", "No hay registros en este momento");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
		Compra findCompra=service.compraById(id);
		Map<String,String> response = new HashMap<>();
		
		if(findCompra != null) {
			return ResponseEntity.ok(findCompra);
		}else {
			response.put("mensaje", "No hay registro con este identificador id="+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}		
		
	}

    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Compra compra) {
		Compra compraNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			compraNew = service.compraSave(compra);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","El Customer ha sido creado con exito!");
		response.put("compra", compraNew);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
    @PutMapping("/edit/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Compra compra) {
		Compra compraUpdate = null;
		Map<String,Object> response = new HashMap<>();
		Compra editCompra = service.compraById(id);
		
		if(editCompra==null) {
			response.put("mensaje","No existe el registro con id:"+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		try {
			
			editCompra.setArticulo(compra.getArticulo());
			editCompra.setCantidad(compra.getCantidad());
			editCompra.setCliente(compra.getCliente());
			editCompra.setFecha(compra.getFecha());
			editCompra.setIva(compra.getIva());
			editCompra.setTotal(compra.getTotal());
			editCompra.setTotal_iva(compra.getTotal_iva());
			
			compraUpdate= service.compraSave(editCompra);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","La Compra ha sido actualizado con exito");
		response.put("compra", compraUpdate);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
				
	}
	
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Compra compraDrop=null;
		Map<String,Object> response = new HashMap<>();
		
		compraDrop = service.compraById(id);
		
		if(compraDrop==null) {
			response.put("mensaje", "No existe el registro con id:"+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		try {
			service.compraDelete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar la compra en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","La compra ha sido borrado con éxito!");
		response.put("compra", compraDrop);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}


