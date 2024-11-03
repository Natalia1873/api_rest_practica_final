package com.keepcoding.api_rest_practica_final.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="articulo")
public class Articulo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false,length = 100)
	private String nombre;
	
	@Column(nullable = false,length = 100)
	private String descripcion;
	
	@Column(nullable = false,length = 100)
	private long unidad_precio;
	
	@Column(nullable = false,length = 100)
	private int unidad_stock;
	
	@Column(nullable = false,length = 100)
	private String tipo;
	
	@Column(nullable = false,length = 100)
	private String proveedor;
	
	private Date fecha;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
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


	public long getUnidad_precio() {
		return unidad_precio;
	}

	
	public void setUnidad_precio(long unidad_precio) {
		this.unidad_precio = unidad_precio;
	}


	public int getUnidad_stock() {
		return unidad_stock;
	}


	public void setUnidad_stock(int unidad_stock) {
		this.unidad_stock = unidad_stock;
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



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
