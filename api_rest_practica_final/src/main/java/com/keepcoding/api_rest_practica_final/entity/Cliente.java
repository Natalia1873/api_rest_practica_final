package com.keepcoding.api_rest_practica_final.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false,length = 100)
	private String nombre;
	
	@Column(nullable = false,length = 100)
	private String apellido;
	
	@Column(nullable = false,length = 100)
	private String empresa;
	
	@Column(nullable = false,length = 100)
	private String puesto;
	
	@Column(nullable = false,length = 100)
	private String direccion;
	
	@Column(nullable = false,length = 100)
	private int codigo_postal;
	
	@Column(nullable = false,length = 100)
	private String provincia;
	
	@Column(nullable = false,length = 100)
	private String telefono;
	
	private LocalDate fecha_nacimiento;
	
	
	
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



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getEmpresa() {
		return empresa;
	}



	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}



	public String getPuesto() {
		return puesto;
	}



	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public int getCodigo_postal() {
		return codigo_postal;
	}



	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}



	public String getProvincia() {
		return provincia;
	}



	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}



	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

}
