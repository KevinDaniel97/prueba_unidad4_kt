package com.uce.edu.demo.repository.modelo;

import java.io.Serializable;

public class ProductoTo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoBarras;
	private Integer stock;
	private String nombre;
	private String categoria;

	public ProductoTo() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductoTo(String codigoBarras, Integer stock, String nombre, String categoria) {
		super();
		this.codigoBarras = codigoBarras;
		this.stock = stock;
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
	//SET Y GET
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
