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
import com.keepcoding.api_rest_practica_final.entity.Articulo;
import com.keepcoding.api_rest_practica_final.service.ArticuloService;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Articulo> articulos = articuloService.allArticulos();
            if (articulos != null && !articulos.isEmpty()) {
                return new ResponseEntity<>(articulos, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "No hay artículos registrados");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error al obtener los artículos: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Articulo articulo = articuloService.articuloById(id);
            if (articulo != null) {
                return new ResponseEntity<>(articulo, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "Artículo no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error al obtener el artículo: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Articulo articulo) {
        try {
            Articulo nuevoArticulo = articuloService.articuloSave(articulo);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Artículo creado con éxito");
            response.put("articulo", nuevoArticulo);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al crear el artículo");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Articulo articulo) {
        try {
            Articulo articuloActual = articuloService.articuloById(id);
            if (articuloActual == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "No existe el artículo con ID: " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            articuloActual.setNombre(articulo.getNombre());
            articuloActual.setDescripcion(articulo.getDescripcion());
            articuloActual.setUnidadPrecio(articulo.getUnidadPrecio());
            articuloActual.setUnidadStock(articulo.getUnidadStock());
            articuloActual.setTipo(articulo.getTipo());
            articuloActual.setProveedor(articulo.getProveedor());
            articuloActual.setFecha(articulo.getFecha());

            Articulo articuloUpdated = articuloService.articuloSave(articuloActual);
            
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Artículo actualizado con éxito");
            response.put("articulo", articuloUpdated);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al actualizar el artículo");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Articulo articulo = articuloService.articuloById(id);
            if (articulo == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "No existe el artículo con ID: " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            articuloService.articuloDelete(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Artículo eliminado con éxito");
            response.put("articulo", articulo);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al eliminar el artículo");
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
