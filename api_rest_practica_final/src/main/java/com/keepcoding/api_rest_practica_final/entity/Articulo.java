package com.keepcoding.api_rest_practica_final.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo implements Serializable {
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "unidad_precio", nullable = false)
    private Double unidadPrecio;

   
    @Column(name = "unidad_stock", nullable = false)
    private Integer unidadStock;

    
    @Column(nullable = false, length = 50)
    private String tipo;

    
    @Column(nullable = false, length = 100)
    private String proveedor;

    @Column(name = "fecha")
    private LocalDate fecha;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    private List<Compra> compras;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getUnidadPrecio() {
		return unidadPrecio;
	}

	public void setUnidadPrecio(Double unidadPrecio) {
		this.unidadPrecio = unidadPrecio;
	}

	public Integer getUnidadStock() {
		return unidadStock;
	}

	public void setUnidadStock(Integer unidadStock) {
		this.unidadStock = unidadStock;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	private static final long serialVersionUID = 1L;
    
}