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

import com.keepcoding.api_rest_practica_final.entity.Articulo;
import com.keepcoding.api_rest_practica_final.service.ArticuloService;


@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {
	
	@Autowired
    private ArticuloService service;

	
	@GetMapping
    public ResponseEntity<?>  index() {
		List<Articulo> lista = service.allArticulos();
		
		Map<String,String> response = new HashMap<>();
		
		if(lista.size()>0) {
			return ResponseEntity.ok(service.allArticulos());
		}else {
			response.put("mensaje", "No hay registros en este momento");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
    

	@GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
		Articulo findArticulo=service.articuloById(id);
		Map<String,String> response = new HashMap<>();
		
		if(findArticulo != null) {
			return ResponseEntity.ok(findArticulo);
		}else {
			response.put("mensaje", "No hay registro con este identificador id="+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}		
		
	}

	@PostMapping
    public ResponseEntity<?> save(@RequestBody Articulo articulo) {
		Articulo articuloNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			articuloNew = service.articuloSave(articulo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insert en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","El Articulo ha sido creado con exito!");
		response.put("articulo", articuloNew);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Articulo articulo) {
		Articulo articuloUpdate = null;
		Map<String,Object> response = new HashMap<>();
		Articulo editArticulo = service.articuloById(id);
		
		if(editArticulo==null) {
			response.put("mensaje","No existe el registro con id:"+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		try {
			
			editArticulo.setCompras(articulo.getCompras());
			editArticulo.setDescripcion(articulo.getDescripcion());
			editArticulo.setFecha(articulo.getFecha());
			editArticulo.setNombre(articulo.getNombre());
			editArticulo.setProveedor(articulo.getProveedor());
			editArticulo.setTipo(articulo.getTipo());
			editArticulo.setUnidadPrecio(articulo.getUnidadPrecio());
			editArticulo.setUnidadStock(articulo.getUnidadStock());
			
			
			articuloUpdate= service.articuloSave(editArticulo);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","La Articulo ha sido actualizado con exito");
		response.put("articulo", articuloUpdate);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
				
	}
	
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Articulo articuloDrop=null;
		Map<String,Object> response = new HashMap<>();
		
		articuloDrop = service.articuloById(id);
		
		if(articuloDrop==null) {
			response.put("mensaje", "No existe el registro con id:"+id);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		try {
			service.articuloDelete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar el articulo en base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		response.put("mensaje","El articulo ha sido borrado con éxito!");
		response.put("articulo", articuloDrop);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}





