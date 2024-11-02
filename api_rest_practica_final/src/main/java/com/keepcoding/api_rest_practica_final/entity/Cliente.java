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
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

}
