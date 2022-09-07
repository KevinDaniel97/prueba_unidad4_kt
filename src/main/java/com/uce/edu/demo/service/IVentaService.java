package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.ProductoRealizarVenta;
import com.uce.edu.demo.repository.modelo.VentaReporte;

public interface IVentaService {
	public void realizarVenta(String numeroVenta, String cedulaCliente, List<ProductoRealizarVenta> productos);
	public List<VentaReporte> reporteVentas(LocalDateTime fecha, String categoria, Integer cantidad);
}
