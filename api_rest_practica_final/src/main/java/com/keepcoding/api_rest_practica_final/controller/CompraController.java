package com.keepcoding.api_rest_practica_final.controller;

import java.time.LocalDate;
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
import com.keepcoding.api_rest_practica_final.entity.Cliente;
import com.keepcoding.api_rest_practica_final.entity.Compra;
import com.keepcoding.api_rest_practica_final.service.ArticuloService;
import com.keepcoding.api_rest_practica_final.service.ClienteService;
import com.keepcoding.api_rest_practica_final.service.CompraService;


@RestController
@RequestMapping("/api/compras")
public class CompraController {
	
	@Autowired
    private CompraService service;
	
	@Autowired
    private ClienteService clienteService;
	
	@Autowired
    private ArticuloService articuloService;

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
    public ResponseEntity<?> save(@RequestBody Map<String, Object> request) {
        try {
            Compra compra = new Compra();
            compra.setFecha(LocalDate.parse((String) request.get("fecha")));
            compra.setCantidad((Integer) request.get("cantidad"));
            compra.setTotal((Double) request.get("total"));
            compra.setIva((Double) request.get("iva"));
            compra.setTotal_iva((Double) request.get("total_iva"));

            
            Long clienteId = Long.valueOf(request.get("cliente_id").toString());
            Cliente cliente = clienteService.clienteById(clienteId);
            if(cliente == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "Cliente no encontrado");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            compra.setCliente(cliente);

           
            Long articuloId = Long.valueOf(request.get("articulo_id").toString());
            Articulo articulo = articuloService.articuloById(articuloId);
            if(articulo == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "Artículo no encontrado");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            compra.setArticulo(articulo);

            Compra compraNueva = service.compraSave(compra);
            
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "La compra ha sido creada con éxito!");
            response.put("compra", compraNueva);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al crear la compra");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> request) {
       try {
           
           Compra compraActual = service.compraById(id);
           if(compraActual == null) {
               Map<String, String> response = new HashMap<>();
               response.put("mensaje", "No existe la compra con id: " + id);
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
           }

           
           compraActual.setFecha(LocalDate.parse((String) request.get("fecha")));
           compraActual.setCantidad((Integer) request.get("cantidad"));
           compraActual.setTotal((Double) request.get("total"));
           compraActual.setIva((Double) request.get("iva"));
           compraActual.setTotal_iva((Double) request.get("total_iva"));

           
           Long clienteId = Long.valueOf(request.get("cliente_id").toString());
           Cliente cliente = clienteService.clienteById(clienteId);
           if(cliente == null) {
               Map<String, String> response = new HashMap<>();
               response.put("mensaje", "Cliente no encontrado");
               return ResponseEntity.badRequest().body(response);
           }
           compraActual.setCliente(cliente);

           
           Long articuloId = Long.valueOf(request.get("articulo_id").toString());
           Articulo articulo = articuloService.articuloById(articuloId);
           if(articulo == null) {
               Map<String, String> response = new HashMap<>();
               response.put("mensaje", "Artículo no encontrado");
               return ResponseEntity.badRequest().body(response);
           }
           compraActual.setArticulo(articulo);

           
           Compra compraUpdated = service.compraSave(compraActual);
           
           Map<String, Object> response = new HashMap<>();
           response.put("mensaje", "La compra ha sido actualizada con éxito");
           response.put("compra", compraUpdated);
           
           return ResponseEntity.ok(response);

       } catch (Exception e) {
           Map<String, Object> response = new HashMap<>();
           response.put("mensaje", "Error al actualizar la compra");
           response.put("error", e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
       }
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


