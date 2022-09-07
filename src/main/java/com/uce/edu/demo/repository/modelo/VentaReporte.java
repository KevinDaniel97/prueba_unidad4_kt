package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

public class VentaReporte {
	
	private String codigoBarras;
	private String nombreProducto;
	private Integer cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal subTotal;
	
	public VentaReporte() {
		// TODO Auto-generated constructor stub
	}
	
	public VentaReporte(String codigoBarras, String nombreProducto, Integer cantidad, BigDecimal precioUnitario,
			BigDecimal subTotal) {
		super();
		this.codigoBarras = codigoBarras;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subTotal = subTotal;
	}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	
	

}
