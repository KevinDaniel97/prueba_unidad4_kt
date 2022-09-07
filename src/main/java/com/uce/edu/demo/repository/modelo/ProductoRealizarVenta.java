package com.uce.edu.demo.repository.modelo;

public class ProductoRealizarVenta {
	
	private String codigoBarras;
	private Integer cantidad;
	
	public ProductoRealizarVenta(String codigoBarras, Integer cantidad) {
		super();
		this.codigoBarras = codigoBarras;
		this.cantidad = cantidad;
	}

	public ProductoRealizarVenta() {
		// TODO Auto-generated constructor stub
	}

	//SET Y GET
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
	
}
