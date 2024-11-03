package com.keepcoding.api_rest_practica_final.entity;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="compra")
public class Compra implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date fecha;
	
	@Column(nullable = false,length = 100)
	private int cantidad;
	
	@Column(nullable = false,length = 100)
	private long total;
	
	@Column(nullable = false,length = 100)
	private int iva;
	
	@Column(nullable = false,length = 100)
	private int total_iva;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Articulo articulo;
		
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public long getTotal() {
		return total;
	}


	public void setTotal(long total) {
		this.total = total;
	}


	public int getIva() {
		return iva;
	}


	public void setIva(int iva) {
		this.iva = iva;
	}


	public int getTotal_iva() {
		return total_iva;
	}


	public void setTotal_iva(int total_iva) {
		this.total_iva = total_iva;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Articulo getArticulo() {
		return articulo;
	}


	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
